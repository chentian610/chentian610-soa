package com.cf.dao;

import java.util.Map;

import com.cf.entity.ScheduleJobEntity;

/**
 * 定时任务
 * 
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
