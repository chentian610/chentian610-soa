package com.chentian610.framework;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 用来统一封装异常信息，可以在产生异常的时候同时将错误信息进行封装成需要的格式：比如JSON、XML等
 * @author com.chentian610
 */
public class BaseController {
	private static Log logger = LogFactory.getLog(BaseController.class);
	/**
	* 异常控制，这便是异常细节可控，将来可用于支持国际化（异常信息国际化）
	**/
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody Object handleException(Exception ex, HttpServletRequest request) {
		//写入错误日志
		logger.error(ex.getMessage(), ex);
		if (ex instanceof CacheException)
			return ResponseUtils.sendFailure(ex.getMessage(),100);
		else if (ex instanceof BusinessException)
			return ResponseUtils.sendFailure(ex.getMessage());
		else
			return ResponseUtils.sendFailure(MsgService.getMsg("UNEXPECTED_EXCEPTION"));
	}
}
