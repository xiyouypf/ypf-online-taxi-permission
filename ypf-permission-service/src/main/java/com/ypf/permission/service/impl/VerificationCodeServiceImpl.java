package com.ypf.permission.service.impl;

import com.ypf.common.enums.ExceptionCodeEnum;
import com.ypf.common.lock.RedisKeyEnum;
import com.ypf.permission.exception.PermissionException;
import com.ypf.permission.service.VerificationCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ypf
 * @date 2023/05/04 19:32
 */
@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Boolean generateVerificationCode(String phone, Integer identityType) {
            // 获取验证码
            String verificationCode = getVerificationCode(6);
            log.info("verificationCode = {}", verificationCode);
            // 存入redis
            String redisKey = getRedisKey(phone, identityType);
            redisTemplate.opsForValue().setIfAbsent(redisKey, verificationCode, RedisKeyEnum.VERIFICATION_NUMBER.getTimeOut(), TimeUnit.SECONDS);
            // 通过短信服务商，将对应的验证码发送到手机上。阿里短信服务，腾讯短信通，华信，容联
        return Boolean.TRUE;
    }

    @Override
    public Boolean checkCode(String phone, Integer identityType, String verificationCode) {
        String redisKey = getRedisKey(phone, identityType);
        String verificationCodeFromRedis = redisTemplate.opsForValue().get(redisKey);
        log.info("redis中的key={},value={}", redisKey, verificationCode);
        if (StringUtils.isBlank(verificationCodeFromRedis)
//                    || !Objects.equals(verificationCodeFromRedis, verificationCode)
        ) {
            throw new PermissionException(ExceptionCodeEnum.VERIFICATION_CODE_ERROR.getCode(), ExceptionCodeEnum.VERIFICATION_CODE_ERROR.getMsg());
        }
        return Boolean.TRUE;
    }

    private String getVerificationCode(int size) {
        // 生成验证码
        double mathRandom = (Math.random() * 9 + 1) * (Math.pow(10, size - 1));
        int resultInt = (int) mathRandom;
        return String.valueOf(resultInt);
    }

    private String getRedisKey(String phone, Integer identityType) {
        return RedisKeyEnum.VERIFICATION_NUMBER.format(phone, String.valueOf(identityType));
    }
}
