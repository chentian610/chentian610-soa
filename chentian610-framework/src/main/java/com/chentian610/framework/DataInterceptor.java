package com.chentian610.framework;

import com.chentian610.common.Constants;
import com.chentian610.common.util.ActionParam;
import com.chentian610.common.util.ActionUtil;
import com.chentian610.common.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一请求拦截器，在这里进行一些基础框架数据的处理。。。。
 * @author Chenth
 */
public class DataInterceptor extends BaseController implements HandlerInterceptor{
	
	protected static Log logger = LogFactory.getLog(DataInterceptor.class);
	public static final String PAGINATION_BEAN_ATTRIBUTE = "pagination_bean";

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler)  throws Exception {
		String requestUrl = request.getRequestURI().replaceFirst(request.getContextPath(), "");  
		logger.info("**********************REQUEST-URL:"+requestUrl+"***********************");
		logger.info("**********************IP-ADRESS:"+(request.getHeader("X-Real-IP")==null?request.getRemoteAddr():request.getHeader("X-Real-IP"))+",SessionID:"+request.getSession().getId());
		String app_type = getStringValue(request, "app_type");
		Integer user_id = null,school_id = null,student_id = null;
		String user_type = null;
		//将本地公共参数存放到Session
		setSessionValue(request);
		/**
		 * 如果登录客户端类型没有设置或者为Web端
		 */
		user_id = 	getIntegerValue(request, "user_id");
		user_type= getStringValue(request, "user_type");
		school_id=	getIntegerValue(request, "school_id");
		student_id=	getIntegerValue(request, "student_id");
		
		ActionParam p = new ActionParam(false);
		p.setApp_type(app_type);
		p.setUser_type(user_type);
		p.setSchool_id(school_id);
		p.setUser_id(user_id);
		p.setStudent_id(student_id);
		ActionUtil.setActionParam(p);
		p.setParam(getParamFromRequest(request));
		setPaginationInfo(request);
		return true;
	}

	/**
	 * 将请求里面的所有参数设置到Param中
	 * @return
	 */
	private HashMap<String, String> getParamFromRequest(HttpServletRequest request) {
		HashMap<String, String> param = new HashMap<String, String>();
		Map<?, ?> map=request.getParameterMap();  
	    for (Object key : map.keySet()) {
	    	logger.info("Param:"+key.toString()+"="+request.getParameter(key.toString()));
	    	param.put(key.toString(), request.getParameter(key.toString()));
	    }
		return param;
	}
	

	/**
	 * 将分页参数设置到线程中
	 * @param request
	 */
	private void setPaginationInfo(HttpServletRequest request) {
		String page = request.getParameter("page");
		String direction = request.getParameter("direction");
		Integer limit = StringUtil.isEmpty(request.getParameter("limit"))?Constants.DEFAULT_LIMIT:Integer.parseInt(request.getParameter("limit"));
		Integer start_id = StringUtil.isEmpty(request.getParameter("start_id"))?Constants.DEFAULT_START:Integer.parseInt(request.getParameter("start_id"));
		boolean isPage_web = !StringUtil.isEmpty(page);//Web端的分页，一般带有第几页
		boolean isPage_app = !StringUtil.isEmpty(direction);//手机APP的分页，一般通过向前或者向后来取数
		boolean isSort_up = Constants.SORT_UP.equals(request.getParameter("sort"));
		boolean isDirection_pre = Constants.DIRECTION_PRE.equals(direction);
		ActionUtil.setPage_app(isPage_app);
		ActionUtil.setPage_web(isPage_web);
		//是否升序，默认降序
		ActionUtil.setSort_up(isSort_up);
		ActionUtil.setDirection_pre(isDirection_pre);
		if (isPage_web) {
			ActionUtil.getActionParam().setStart((Integer.parseInt(page)-1)*limit);
			ActionUtil.getActionParam().setLimit(limit);
		} else if (isPage_app) {
			ActionUtil.getActionParam().setStart(0);
			ActionUtil.getActionParam().setLimit(limit);
			ActionUtil.getActionParam().setDirection(direction);
			if (isSort_up) {//升序
				ActionUtil.getParameterMap().put("app_sql", (isDirection_pre?"<":">") + start_id);
				//如果是往前取，那么先按照倒序取出数据，最后再倒排list
				ActionUtil.getParameterMap().put("order_sql", isDirection_pre?"DESC":"ASC");
			} else {
				ActionUtil.getParameterMap().put("app_sql", (isDirection_pre?">":"<") + start_id);
				//如果是往前取，那么先按照升序取出数据，最后再倒排list
				ActionUtil.getParameterMap().put("order_sql", isDirection_pre?"ASC":"DESC");
			}
			if (start_id==0) {
				ActionUtil.getActionParam().setStart(-1);
				ActionUtil.getParameterMap().put("app_sql", null);
				ActionUtil.getParameterMap().put("order_sql", isSort_up?"ASC":"DESC");
			}
		}
	}

	/**
	 * 将传递过来的参数存放到Session中
	 * @param request
	 */
	private void setSessionValue(HttpServletRequest request) {
		if (request.getSession().getAttribute("user_id")==null && request.getParameter("user_id")!=null)
			request.getSession().setAttribute("user_id", request.getParameter("user_id"));
		if (request.getSession().getAttribute("school_id")==null && request.getParameter("school_id")!=null)
			request.getSession().setAttribute("school_id", request.getParameter("school_id"));
		if (request.getSession().getAttribute("user_type")==null && request.getParameter("user_type")!=null)
			request.getSession().setAttribute("user_type", request.getParameter("user_type"));
		if (request.getSession().getAttribute("app_type")==null && request.getParameter("app_type")!=null)
			request.getSession().setAttribute("app_type", request.getParameter("app_type"));
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

	/**
	 * 获取某个参数的整数值
	 * @param request
	 * @param field 参数
	 * @return Integer
	 */
	private static Integer getIntegerValue(HttpServletRequest request,String field) {
		String requestValue = request.getParameter(field);
		if (StringUtil.isEmpty(requestValue)) {
			Object sessionValue =  request.getSession().getAttribute(field);
			requestValue = sessionValue + "";
		}
		try {
			return Integer.parseInt(requestValue);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 将请求里面的所有参数设置到Param中
	 * @return
	 */
	private static String getStringValue(HttpServletRequest request,String field) {
		String requestValue = request.getParameter(field);
		if (StringUtil.isEmpty(requestValue)) {
			Object sessionValue =  request.getSession().getAttribute(field);
			if (sessionValue==null) return "0";
			else return sessionValue+"";
		} return requestValue;
	}
}
