package com.ypf.permission.service;

import com.ypf.common.response.ServiceResponse;

/**
 * @author ypf
 * @date 2023/05/06 22:24
 */
public interface VerificationCodeService {
    /**
     * 生成验证码
     */
    Boolean generateVerificationCode(String phone, Integer identityType);

    /**
     * 校验验证码
     */
    Boolean checkCode(String phone, Integer identityType, String verificationCode);
}
