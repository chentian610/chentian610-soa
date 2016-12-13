package com.chentian610.framework;

import com.chentian610.common.util.ActionUtil;
import com.chentian610.common.util.RedisKeyUtil;
import com.chentian610.common.util.SpringBeanUtil;
import com.chentian610.common.util.StringUtil;
import java.util.HashMap;

public class SchoolConfig {

	//获取学校配置
	public  static String getProperty(String key) {
		if (StringUtil.isNotEmpty(key)) {
			JedisDAO jedisDao = SpringBeanUtil.getBean(JedisDAO.class);
			String configKey=RedisKeyUtil.getSchoolConfigKey(ActionUtil.getSchoolID());
			if (jedisDao.hexists(configKey, key)) 
				return jedisDao.hget(configKey, key);
			else {
				GeneralDAO dao = SpringBeanUtil.getBean(GeneralDAO.class);
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("school_id", ActionUtil.getSchoolID());
				map.put("config_key", key);
				String config_value=dao.queryObject("schoolConfigMap.getSchoolConfig", map);
				if (StringUtil.isEmpty(config_value)) return null;
				jedisDao.hset(configKey, key, config_value);
				return config_value;
			}
		}
		return null;
	}
}
