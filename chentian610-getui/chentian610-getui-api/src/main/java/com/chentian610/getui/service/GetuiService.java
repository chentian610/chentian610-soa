package com.chentian610.chentian610.getui.service;

import com.chentian610.common.vo.ReceiveVO;

import java.util.HashMap;
import java.util.List;

public interface GetuiService {
	/**
	 * 推送给个人
	 * @param dataMap 数据
	 * @param user_id 用户ID
	 */
	public void pushMessage(HashMap<String, String> dataMap, Integer user_id);

	/**
	 * 给一组人发推送
	 * @param dataMap 数据
	 * @param userList 接收团队列表
	 */
	public void pushMessageByList(HashMap<String, String> dataMap, List<String> userList);

	/**
	 * 给一组人发推送
	 * @param dataMap 数据
	 * @param receiveList 接收团队列表
	 */
	public void pushMessage(HashMap<String, String> dataMap, List<ReceiveVO> receiveList);
	
	/**
	 * 通过学生向家长推送消息
	 * @param dataMap 数据
	 * @param student_id 学生ID
	 */
	public void pushMessageByStuID(HashMap<String, String> dataMap, Integer student_id);

	/**
	 * 向学校所有用户分组发送推送
	 * @param dataMap 数据
	 * @param user_type 用户类型
	 */
	public void pushMessage(HashMap<String, String> dataMap, String user_type);
}
