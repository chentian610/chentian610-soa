package com.chentian610.framework;

import java.util.HashMap;
import java.util.List;

import com.chentian610.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

public class SystemConfig {

	private static Logger _logger = LoggerFactory.getLogger(SystemConfig.class);

	private static HashMap<String,String> configMap = new HashMap<String,String>();
	
	static {
		try {
			_logger.error("开始从数据库加载本地配置............");
			GeneralDAO dao = (GeneralDAO) ContextLoaderListener.getCurrentWebApplicationContext().getBean("GeneralDAO");
			List<HashMap<String,String>> list = dao.queryForList("systemConfigMap.getConfigList");
			for (HashMap<String,String> map:list) configMap.put(map.get("config_key"),map.get("config_value"));
		} catch (Exception e) {
			_logger.error(e.getMessage(),e);
		}
	}

	public static String getProperty(String key) {
		if (StringUtil.isNotEmpty(key)) {
			return configMap.get(key);
		}
		return null;
	}
}
