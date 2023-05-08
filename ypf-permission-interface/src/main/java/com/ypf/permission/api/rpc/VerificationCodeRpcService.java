package com.ypf.permission.api.rpc;

import com.ypf.common.response.ServiceResponse;
import com.ypf.permission.model.dto.VerificationCodeCheckDTO;
import com.ypf.permission.model.dto.VerificationCodeGenerateDTO;

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
