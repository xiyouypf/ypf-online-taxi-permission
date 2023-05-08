package com.ypf.permission.rpc;

import com.ypf.common.enums.ExceptionCodeEnum;
import com.ypf.common.enums.UserTypeEnum;
import com.ypf.common.response.ServiceResponse;
import com.ypf.permission.api.VerificationCodeRpcService;
import com.ypf.permission.exception.PermissionException;
import com.ypf.permission.model.param.BaseVerificationCodeDTO;
import com.ypf.permission.model.param.VerificationCodeCheckDTO;
import com.ypf.permission.model.param.VerificationCodeGenerateDTO;
import com.ypf.permission.service.impl.VerificationCodeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.Service;

import java.util.Objects;

/**
 * @author ypf
 * @date 2023/05/06 21:59
 */
@Slf4j
@Service
public class VerificationCodeRpcServiceImpl implements VerificationCodeRpcService {

    @Autowired
    private VerificationCodeServiceImpl verificationCodeServiceImpl;

    @Override
    public ServiceResponse<Boolean> generateVerificationCode(VerificationCodeGenerateDTO generateDTO) {
        try {
            checkParam(generateDTO);
            Boolean generateVerificationCodeSuccess = verificationCodeServiceImpl.generateVerificationCode(generateDTO.getPhone(), UserTypeEnum.DRIVER_IDENTITY.getIdentityType());
            return ServiceResponse.buildSuccessResponse(generateVerificationCodeSuccess);
        } catch (PermissionException e) {
            log.info("获取验证码程序异常generateDTO = {}", generateDTO);
            return ServiceResponse.buildErrorResponse(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.info("获取验证码系统异常", e);
            return ServiceResponse.buildErrorResponse(ExceptionCodeEnum.ERROR.getCode(), ExceptionCodeEnum.VERIFICATION_CODE_ERROR.getMsg());
        }
    }

    @Override
    public ServiceResponse<Boolean> checkVerificationCode(VerificationCodeCheckDTO checkDTO) {
        try {
            checkParam(checkDTO);
            Boolean checkVerificationCodeSuccess = verificationCodeServiceImpl.checkCode(checkDTO.getPhone(), checkDTO.getIdentityType(), checkDTO.getVerificationCode());
            return ServiceResponse.buildSuccessResponse(checkVerificationCodeSuccess);
        } catch (PermissionException e) {
            log.info("校验验证码程序异常checkDTO = {}", checkDTO);
            return ServiceResponse.buildErrorResponse(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.info("校验验证码系统异常", e);
            return ServiceResponse.buildErrorResponse(ExceptionCodeEnum.ERROR.getCode(), ExceptionCodeEnum.VERIFICATION_CODE_ERROR.getMsg());
        }
    }

    private void checkParam(BaseVerificationCodeDTO param) {
        if (!Objects.equals(param.getIdentityType(), UserTypeEnum.DRIVER_IDENTITY.getIdentityType())) {
            throw new PermissionException(ExceptionCodeEnum.VERIFICATION_CODE_ERROR.getCode(), "参数错误");
        }
    }
}
