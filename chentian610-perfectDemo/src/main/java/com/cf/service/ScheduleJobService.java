package com.cf.service;

import java.util.List;
import java.util.Map;

import com.cf.entity.ScheduleJobEntity;

/**
 * 定时任务
 * 
 */
public interface ScheduleJobService {

	/**
	 * 根据ID，查询定时任务
	 */
	ScheduleJobEntity queryObject(Long jobId);
	
	/**
	 * 查询定时任务列表
	 */
	List<ScheduleJobEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存定时任务
	 */
	void save(ScheduleJobEntity scheduleJob);
	
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJobEntity scheduleJob);
	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(Long[] jobIds);
	
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(Long[] jobIds, int status);
	
	/**
	 * 立即执行
	 */
	void run(Long[] jobIds);
	
	/**
	 * 暂停运行
	 */
	void pause(Long[] jobIds);
	
	/**
	 * 恢复运行
	 */
	void resume(Long[] jobIds);
}
