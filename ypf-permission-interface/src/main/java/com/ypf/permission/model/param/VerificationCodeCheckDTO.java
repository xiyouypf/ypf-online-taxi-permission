package com.ypf.permission.model.param;

import lombok.Data;

/**
 * @author 作者
 * @date 2023/05/05 01:23
 */
@Data
public class VerificationCodeCheckDTO extends BaseVerificationCodeDTO {
    /**
     * 验证码
     */
    private String verificationCode;
}
