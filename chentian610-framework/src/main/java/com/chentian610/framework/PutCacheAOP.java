package com.chentian610.framework;

import com.chentian610.common.util.ActionUtil;
import com.chentian610.common.util.RedisKeyUtil;
import com.chentian610.common.vo.annotation.PutCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class PutCacheAOP {


	@Autowired
	private JedisDAO jedisDAO;
	
	@Pointcut("@annotation(com.chentian610.common.vo.annotation.PutCache)")
	public void PutCache(){
	}
	
	/**
	 * 在所有标注@PutCache的地方切入
	 * @param joinPoint
	 */
	@After("PutCache()")
	public void AfterExec(JoinPoint joinPoint){
		//是否启用缓存
		if (!"TRUE".equals(SystemConfig.getProperty("CACHE_ON"))) return;
		MethodSignature ms=(MethodSignature) joinPoint.getSignature();
		Method method=ms.getMethod();
		String ActionName = RedisKeyUtil.CACHE + method.getAnnotation(PutCache.class).name();
		String fieldList = method.getAnnotation(PutCache.class).value();
		for (String field:fieldList.split(",")) 
		{
			if ("school_id".equals(field))
				ActionName+=":"+ ActionUtil.getSchoolID();
			else if ("user_id".equals(field))
				ActionName+=":"+ActionUtil.getUserID();
			else if ("user_type".equals(field))
				ActionName+=":"+ActionUtil.getUserType();
			else ActionName+=":"+ActionUtil.getParameter(field);
		}
		jedisDAO.incr(ActionName);
	}
}
