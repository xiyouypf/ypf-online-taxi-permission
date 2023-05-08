package com.ypf.permission.api;

import com.ypf.common.response.ServiceResponse;
import com.ypf.permission.model.param.VerificationCodeCheckDTO;
import com.ypf.permission.model.param.VerificationCodeGenerateDTO;

/**
 * @author ypf
 * @date 2023/05/06 21:57
 */
public interface VerificationCodeRpcService {
    /**
     * 生成验证码
     */
    ServiceResponse<Boolean> generateVerificationCode(VerificationCodeGenerateDTO generateDTO);

    /**
     * 校验验证码
     */
    ServiceResponse<Boolean> checkVerificationCode(VerificationCodeCheckDTO checkDTO);
}
