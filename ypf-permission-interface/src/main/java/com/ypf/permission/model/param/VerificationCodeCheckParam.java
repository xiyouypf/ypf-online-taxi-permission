package com.ypf.permission.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 作者
 * @date 2023/05/05 01:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VerificationCodeCheckParam extends BaseVerificationCodeParam {
    /**
     * 验证码
     */
    private String verificationCode;
}
