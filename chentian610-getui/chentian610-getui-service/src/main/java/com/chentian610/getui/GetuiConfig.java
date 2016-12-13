package com.chentian610.chentian610.getui;

import com.chentian610.chentian610.getui.vo.GetuiVO;
import com.chentian610.common.util.SpringBeanUtil;
import com.chentian610.framework.GeneralDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GetuiConfig {

	protected static Logger _logger = LoggerFactory.getLogger(GetuiConfig.class);

	private static ConcurrentHashMap<Integer,GetuiVO> map = new ConcurrentHashMap<Integer,GetuiVO>();

	static {
		try {
			_logger.error("开始从数据库加载个推配置............");
			ApplicationContext context = ContextLoaderListener.getCurrentWebApplicationContext()==null?SpringBeanUtil.getApplicationContext():ContextLoaderListener.getCurrentWebApplicationContext();
			GeneralDAO dao = (GeneralDAO) context.getBean("GeneralDAO");
			if (dao==null) dao = SpringBeanUtil.getBean(GeneralDAO.class);
			List<GetuiVO> list = dao.queryForList("getuiMap.getGetuiList");
			for (GetuiVO vo:list) map.put(vo.getSchool_id(),vo);
		} catch (Exception e) {
			e.printStackTrace();
			_logger.error(e.getMessage());
		}
	}

	public static GetuiVO getProperty(Integer key) {
		if (map.containsKey(key)) return map.get(key);
		ApplicationContext context = ContextLoaderListener.getCurrentWebApplicationContext()==null?SpringBeanUtil.getApplicationContext():ContextLoaderListener.getCurrentWebApplicationContext();
		GeneralDAO dao = (GeneralDAO) context.getBean("GeneralDAO");
		GetuiVO getui = dao.queryObject("getuiMap.getGetuiBySchoolID",key);
		if (getui!=null) setProperty(key,getui);
		return getui;
	}
	
	public static void setProperty(Integer key,GetuiVO vo) {
			map.put(key, vo);
	}
	
	public static void remove(String key) {
			map.remove(key);
	}
}
