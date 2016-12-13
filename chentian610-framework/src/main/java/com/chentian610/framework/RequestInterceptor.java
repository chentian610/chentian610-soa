package com.chentian610.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chentian610.common.util.ActionUtil;
import com.chentian610.common.util.IntegerUtil;
import com.chentian610.common.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一请求拦截器，在这里进行一些基础框架数据的处理。。。。
 * @author Chenth
 */
public class RequestInterceptor extends BaseController implements HandlerInterceptor{
	protected static Log logger = LogFactory.getLog(RequestInterceptor.class);
	public static final String PAGINATION_BEAN_ATTRIBUTE = "pagination_bean";

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler)  throws Exception {
		String app_type=request.getParameter("app_type");
		if (IntegerUtil.isEmpty(ActionUtil.getUserID())){
			if (StringUtil.isEmpty(app_type)) {//web端
				String header=request.getHeader("x-requested-with");
				if (StringUtil.isNotEmpty(header)) {//ajax请求
					response.setHeader("sessionstatus", "timeout");//在响应头设置session状态   
					response.setHeader("url", request.getServerName());//服务器域名
				} 
			}
			throw new RuntimeException("您还未登陆，请先登录系统......");
			}
		else return true;
	}


	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
