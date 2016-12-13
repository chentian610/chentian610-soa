package com.chentian610.framework;

import com.chentian610.common.util.ActionUtil;
import com.chentian610.common.util.RedisKeyUtil;
import com.chentian610.common.vo.annotation.GetCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class GetCacheAOP {

	@Autowired
	private JedisDAO jedisDAO;

	@Pointcut("@annotation(com.chentian610.common.vo.annotation.GetCache)")
	public void getCache(){
	}

	/**
	 * 在所有标注@getCache的地方切入
	 * @param joinPoint
	 */
	@Before("getCache()")
	public void beforeExec(JoinPoint joinPoint) {
		//是否启用缓存
		if (!"TRUE".equals(SystemConfig.getProperty("CACHE_ON"))) return;
		MethodSignature ms=(MethodSignature) joinPoint.getSignature();
		Method method=ms.getMethod();
		String ActionName = RedisKeyUtil.CACHE + method.getAnnotation(GetCache.class).name();
		String fieldList = method.getAnnotation(GetCache.class).value();
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
		ActionUtil.setCache(true);
		//如果是第一次取值.则将版本存放到redis数据库
		if (jedisDAO.exists(ActionName))  {
			if (jedisDAO.get(ActionName).equals(ActionUtil.getParameter("cache_version")))
				throw new CacheException("数据没有更新,可以采用本地缓存数据!");
			ActionUtil.setCache_version(Long.parseLong(jedisDAO.get(ActionName)));
		} else ActionUtil.setCache_version(jedisDAO.incr(ActionName));
	}
}
