package com.chentian610.getui.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.alibaba.dubbo.config.annotation.Service;
import com.chentian610.getui.service.GetuiService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chentian610.getui.vo.GetuiPush;
import com.chentian610.common.vo.ReceiveVO;
import com.chentian610.common.DictConstants;
import com.chentian610.common.util.ActionUtil;
import com.chentian610.framework.GeneralDAO;
import com.chentian610.common.util.IntegerUtil;

@Service
public class GetuiServiceImpl implements GetuiService {

	@Autowired
	private GeneralDAO dao;


	/**
	 * 推送给个人
	 * @param dataMap 数据
	 * @param user_id 用户ID
	 */
	public void pushMessage(HashMap<String,String> dataMap,Integer user_id){
		System.out.println("oooooooooooooooooooooooo");
		List<String> list = new ArrayList<String>();
		list.add(user_id+"");
		dataMap.put("user_type", DictConstants.USERTYPE_ALL);
		GetuiPush ptt1 = new GetuiPush(dataMap,list);
		Thread thread1 = new Thread(ptt1);
		thread1.start();
	}

	/**
	 * 给一组人发推送
	 * @param dataMap 数据
	 * @param userList 接收团队列表
	 */
	public void pushMessageByList(HashMap<String,String> dataMap,List<String> userList){
		GetuiPush ptt1 = new GetuiPush(dataMap,userList);
		Thread thread1 = new Thread(ptt1);
		thread1.start();
	}

	@Override
	public void pushMessage(HashMap<String, String> dataMap,List<ReceiveVO> receivelist) {
		HashMap<String,HashMap<String,String>> teacherMap = new HashMap<String,HashMap<String,String>>();
		HashMap<String,HashMap<String,String>> parentMap = new HashMap<String,HashMap<String,String>>();
		Integer school_id = IntegerUtil.getValue(dataMap.get("school_id"))==null?IntegerUtil.getValue(dataMap.get("school_id")):IntegerUtil.getValue(dataMap.get("school_id"));
		for (ReceiveVO vo:receivelist) {
			//如果是学生，那么发送给对应的家长
			if (DictConstants.USERTYPE_STUDENT.equals(vo.getUser_type())) {
				if (IntegerUtil.isNotEmpty(vo.getStudent_id())) {
					HashMap<String,String> extendMap = new HashMap<String,String>();
					extendMap.put("student_id", vo.getStudent_id().toString());
					parentMap.putAll(changeListToMap(dao.queryForList("getuiMap.getParentListByStudentID",vo.getStudent_id()),extendMap));
				} else if (DictConstants.TEAM_TYPE_CLASS.equals(vo.getTeam_type())) {
					HashMap<String,String> extendMap = new HashMap<String,String>();
					extendMap.put("group_id", vo.getGroup_id().toString());
					extendMap.put("team_id", vo.getTeam_id().toString());
					extendMap.put("student_id", "0");
					extendMap.put("team_type", DictConstants.TEAM_TYPE_CLASS);
					parentMap.putAll(changeListToMap(dao.queryForList("getuiMap.getParentListByClassID",vo),extendMap));
				} else if (DictConstants.TEAM_TYPE_INTEREST.equals(vo.getTeam_type())) {
					HashMap<String,String> extendMap=new HashMap<String,String>();
					extendMap.put("group_id", "0");
					extendMap.put("team_id", vo.getTeam_id().toString());
					extendMap.put("student_id", "0");
					extendMap.put("team_type", DictConstants.TEAM_TYPE_INTEREST);
					parentMap.putAll(changeListToMap(dao.queryForList("getuiMap.getInterestParent", vo),extendMap));
				}
				/*//寝室不推送，因为寝室都是指定某个孩子的，已经包含在StudentID不为0的情况中
				else {
					parentMap.putAll(changeListToMap(dao.queryForList("getuiMap.getParentListByBedID",vo)));
					dataMap.put("group_id", vo.getGroup_id().toString());
					dataMap.put("group_id", vo.getTeam_id().toString());
					dataMap.put("student_id", "0");
					pushGetui(dataMap, parentMap);
				}*/
			} else if (DictConstants.USERTYPE_TEACHER.equals(vo.getUser_type())) {
				if (IntegerUtil.isNotEmpty(vo.getUser_id())) {
					teacherMap.put(vo.getUser_id().toString(), null);
				} else if (IntegerUtil.isNotEmpty(vo.getTeam_id()) || IntegerUtil.isNotEmpty(vo.getGroup_id())) {
					teacherMap.putAll(changeListToMap(dao.queryForList("getuiMap.getTeacherListByClassID",vo)));
				} else {
					teacherMap.putAll(changeListToMap(dao.queryForList("getuiMap.getTeacherListBySchoolID",school_id)));
				}
			} else {
				if (DictConstants.TEAM_TYPE_CLASS.equals(vo.getTeam_type())) {
					if (IntegerUtil.isNotEmpty(vo.getStudent_id())) {
						HashMap<String,String> extendMap = new HashMap<String,String>();
						extendMap.put("student_id", vo.getStudent_id().toString());
						extendMap.put("user_type", DictConstants.USERTYPE_STUDENT);
						parentMap.putAll(changeListToMap(dao.queryForList("getuiMap.getParentListByStudentID",vo.getStudent_id()),extendMap));
					} else if (IntegerUtil.isNotEmpty(vo.getUser_id())) {
						teacherMap.put(vo.getUser_id().toString(), null);
						dataMap.put("user_type", DictConstants.USERTYPE_TEACHER);
					} else {
						HashMap<String,String> extendMap = new HashMap<String,String>();
						extendMap.put("user_type", DictConstants.USERTYPE_STUDENT);
						parentMap.putAll(changeListToMap(dao.queryForList("getuiMap.getParentListByClassID",vo),extendMap));
						HashMap<String,String> extendMap2 = new HashMap<String,String>();
						extendMap2.put("user_type", DictConstants.USERTYPE_TEACHER);
						teacherMap.putAll(changeListToMap(dao.queryForList("getuiMap.getTeacherListByClassID",vo),extendMap2));
					}
				}
				//else parentMap.putAll(changeListToMap(dao.queryForList("getuiMap.getParentListByBedID",vo)));
			}
		}
		pushTeacherMap(dataMap, teacherMap);
		pushParentMap(teacherMap,parentMap,dataMap);
	}


	/**
	 * 给教师推送个推
	 * @param dataMap
	 * @param teacherMap
	 */
	private void pushTeacherMap(HashMap<String, String> dataMap,
								HashMap<String, HashMap<String, String>> teacherMap) {
		if (!teacherMap.isEmpty()) {
			dataMap.put("user_type", DictConstants.USERTYPE_TEACHER);
			//去掉教师发给自己的消息推送
			if (DictConstants.USERTYPE_TEACHER.equals(ActionUtil.getUserType()))
				teacherMap.remove(ActionUtil.getUserID().toString());
			pushGetui(dataMap, teacherMap);
		}
	}

	/**
	 * 给家长端推送消息，如果同一个账户教师端已经推过，那么家长端不再推送
	 * @param teacherMap
	 * @param parentMap
	 * @param dataMap
	 */
	private void pushParentMap(HashMap<String, HashMap<String, String>> teacherMap,HashMap<String, HashMap<String, String>> parentMap,HashMap<String, String> dataMap) {
		if (parentMap.isEmpty()) return;
		HashMap<String,HashMap<String,String>> parMap = new HashMap<String,HashMap<String,String>>();
		Iterator<Entry<String, HashMap<String, String>>> iter = parentMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, HashMap<String, String>> entry = iter.next();
			if (teacherMap.containsKey(entry.getKey().toString())) continue;
			parMap.put(entry.getKey().toString(), entry.getValue());
		}
		if (!parMap.isEmpty()) {
			dataMap.put("user_type", DictConstants.USERTYPE_STUDENT);
			pushGetui(dataMap, parMap);
		}
	}

	@Override
	public void pushMessage(HashMap<String, String> dataMap,String user_type) {
		Integer school_id = IntegerUtil.getValue(dataMap.get("school_id"))==null?IntegerUtil.getValue(dataMap.get("school_id")):IntegerUtil.getValue(dataMap.get("school_id"));
		//如果是学生，那么发送给对应的家长
		if (DictConstants.USERTYPE_STUDENT.equals(user_type)) {
			HashMap<String, HashMap<String, String>> parentMap = changeListToMap(dao.queryForList("getuiMap.getParentListBySchoolID",school_id));
			dataMap.put("group_id", "0");
			dataMap.put("team_id", "0");
			pushGetui(dataMap, parentMap);
		} else if (DictConstants.USERTYPE_TEACHER.equals(user_type)) {
			HashMap<String, HashMap<String, String>> teacherMap = changeListToMap(dao.queryForList("getuiMap.getTeacherListBySchoolID",school_id));
			dataMap.put("group_id", "0");
			dataMap.put("team_id", "0");
			pushGetui(dataMap, teacherMap);
		} else {
			HashMap<String,HashMap<String,String>> userMap = new HashMap<String,HashMap<String,String>>();
			userMap.putAll(changeListToMap(dao.queryForList("getuiMap.getParentListBySchoolID",school_id),null));
			userMap.putAll(changeListToMap(dao.queryForList("getuiMap.getTeacherListBySchoolID",school_id),null));
			pushGetui(dataMap, userMap);
		}
	}

	private void pushGetui(HashMap<String, String> dataMap,HashMap<String, HashMap<String, String>> userMap) {
		if (userMap.isEmpty()) return;
		HashMap<String,Object> pushMap = new HashMap<String,Object>();
		Iterator<Entry<String, HashMap<String, String>>> iter = userMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, HashMap<String, String>> entry = iter.next();
			String user_id = entry.getKey().toString();
			if (entry.getValue()==null) {
				pushMap.put(user_id, null);
				continue;
			}
			HashMap<String,String> pushDataMap = new HashMap<String,String>();
			pushDataMap.putAll(dataMap);
			HashMap<String, String> extendMap = entry.getValue();
			pushDataMap.putAll(extendMap);
			List<String> accountList = new ArrayList<String>();
			accountList.add(user_id);
			GetuiPush ptt = new GetuiPush(pushDataMap,accountList);
			Thread thread = new Thread(ptt);
			thread.start();
		}
		if (pushMap.isEmpty()) return;
		GetuiPush ptt1 = new GetuiPush(dataMap,pushMap);
		Thread thread1 = new Thread(ptt1);
		thread1.start();
	}

	private HashMap<String, HashMap<String,String>> changeListToMap(List<Object> list) {
		return changeListToMap(list,null);
	}

	private HashMap<String, HashMap<String,String>> changeListToMap(List<Object> list,HashMap<String, String> extendMap) {
		HashMap<String, HashMap<String,String>> dataMap = new HashMap<String, HashMap<String,String>>();
		for (Object user_id: list)
		{
			//含有未注册的教师
			if(user_id==null) continue;
			dataMap.put(user_id.toString(), extendMap);
		}
		return dataMap;
	}

	@Override
	public void pushMessageByStuID(HashMap<String, String> dataMap, Integer student_id) {
		List<String> parentList = dao.queryForList("getuiMap.getParentListByStudentID", student_id);
		dataMap.put("user_type", DictConstants.USERTYPE_STUDENT);
		GetuiPush ptt1 = new GetuiPush(dataMap,parentList);
		Thread thread1 = new Thread(ptt1);
		thread1.start();
	}
}
