package com.geccocrawler.gecco.monitor;

import com.geccocrawler.gecco.spider.render.RenderException;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class RenderMointorIntercetor implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		if(method.getName().equals("inject")) {
			try {
				Object o = proxy.invokeSuper(obj, args);
				return o;
			} catch(RenderException ex) {
				RenderMonitor.incrException(ex.getSpiderBeanClass().getName());
				throw ex;
			}
		} else {
			return proxy.invokeSuper(obj, args);
		}
	}
}
