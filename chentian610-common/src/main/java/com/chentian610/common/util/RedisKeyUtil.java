package com.chentian610.common.util;

import com.chentian610.common.DictConstants;
import com.chentian610.common.vo.ReceiveVO;

import java.util.Map;

public class RedisKeyUtil {
	
	/**
	 * 操作类型:插入操作
	 */
	public static final String OPTYPE_CREATE="C";
	
	/**
	 * 操作类型:更新操作
	 */
	public static final String OPTYPE_UPDATE="U";
	
	/**
	 * 操作类型:读取操作
	 */
	public static final String OPTYPE_READ="R";
	
	/**
	 * 操作类型:删除操作
	 */
	public static final String OPTYPE_DELETE="D";
	
	/**
	 * 计算类型:加法
	 */
	public static final String MATH_ADD="ADD";
	
	/**
	 * 操作类型:减法操作
	 */
	public static final String MATH_SUB="SUB";
	
	
	/**
	 * 动态redis 临时Key前缀
	 */
	public static final String KEY_SEQUENCE_PRE="SEQUENCE:";
	
	/**
	 * 动态redis 已读标记前缀
	 */
	public static final String KEY_READ_PRE="READ:";
	
	/**
	 * 动态redis 已读标记前缀
	 */
	public static final String KEY_SCORE_PRE="SCORE:";
	
	/**
	 * 动态redis 临时Key前缀
	 */
	public static final String KEY_TEMP_PRE="TEMP:";
	
	/**
	 * 动态redis 集合前缀
	 */
	public static final String KEY_SET_PRE="DATA:";
	
	/**
	 * 动态redis Key前缀
	 */
	public static final String KEY_DYNAMIC_PRE="DYNAMIC:";
	
	/**
	 * 动态redis MODULE_CODE
	 */
	public static final String KEY_MODULE_CODE="MODULE_CODE:";
	
	/**
	 * 动态redis PKID
	 */
	public static final String KEY_MODULE_PKID="PKID:";
	
	/**
	 * 通知redis Key前缀
	 */
	public static final String KEY_NOTICE_PRE="NOTICE:";
	
	/**
	 * 通知redis NOTICEID
	 */
	public static final String KEY_NOTICE_ID=":NOTICEID";
	
	/**
	 * 动态redis Key前缀
	 */
	public static final String KEY_DYNAMIC_RECEIVE="RECEIVE_GROUPS";
	
	/**
	 * 动态redis Key前缀
	 */
	public static final String KEY_GROUP_PRE="USER_GROUPS:";
	
	/**
	 * 动态redis Key前缀
	 */
	public static final String REPLY="REPLY:";
	
	/**
	 * 动态redis Key前缀
	 */
	public static final String USERS="USERS:";
	
	/**
	 * 动态redis 缓存
	 */
	public static final String CACHE="CACHE:";
	
	/**
	 * 排名redis Key前缀
	 */
	public static final String KEY_RANK_PRE="RANK:";
	
	/**
	 * 最近扣分记录redis Key前缀
	 */
	public static final String KEY_RECORD_PRE="RECORD:";
	
	/**
	 * 个推reids Key前缀
	 */
	public static final String KEY_GETUI_PRE="GETUI:";
	
	/**
	 * 考勤项目，扣分原因信息redis Key前缀
	 */
	public static final String KEY_CODE_PRE="CODE:";
	
	/**
	 * 学校配置redis Key前缀
	 */
	public static final String KEY_CONFIG_PRE="CONFIG:";
	
	/**
	 * 获取公共参数组装起来的唯一Key
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getUnionKey(){
		return getUnionKey(ActionUtil.getSchoolID(),ActionUtil.getUserType(),ActionUtil.getUserID(),ActionUtil.getStudentID());
	}
	
	/**
	 * 获取公共参数组装起来的唯一Key
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getUnionKey(Integer school_id, String user_type, Integer user_id, Integer student_id){
		if (DictConstants.USERTYPE_PARENT.equals(user_type) || DictConstants.USERTYPE_STUDENT.equals(user_type) )
		return "UNION_KEY:"+"SCHOOL_ID"+school_id+":USER_TYPE"+DictConstants.USERTYPE_STUDENT+":STUDENT_ID"+student_id;
		else return "UNION_KEY:"+"SCHOOL_ID"+school_id+":USER_TYPE"+user_type+":USER_ID"+user_id; 
	}

	/**
	 * 生成分组Key:某个班级的某个用户类型
	 * @例子：GROUP_KEY:SCHOOL_ID1030:USER_TYPE003010:TEAM_TYPE011005:GROUP_ID1005:TEAM_ID1103
	 * @含义：前缀           :学校ID        :用户类型               ：团队类型               :年级或者楼层   :班级或者寝室ID           
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getGroupKey(Integer school_id, String team_type, Integer group_id, Integer team_id) {
		StringBuffer sb = new StringBuffer("GROUP_KEY:SCHOOL_ID"+ActionUtil.getSchoolID());
		sb.append(":TEAM_TYPE"+team_type).append(":GROUP_ID"+group_id).append(":TEAM_ID"+team_id);
		return sb.toString();
	}
	
	/**
	 * 生成分组Key:某个班级的某个用户类型
	 * GROUP_KEY:SCHOOL_ID1030:USER_TYPE003010:TEAM_TYPE011005:GROUP_ID1005:TEAM_ID1103
	 * 前缀:学校ID:用户类型：团队类型:年级或者楼层:班级或者寝室ID   
	 * @return 唯一Key
	 */
	public static String getGroupKey(ReceiveVO vo) {
		StringBuffer sb = new StringBuffer("GROUP_KEY:SCHOOL_ID"+ActionUtil.getSchoolID());
		sb.append(":TEAM_TYPE"+vo.getTeam_type()).append(":GROUP_ID"+(vo.getGroup_id()==null?0:vo.getGroup_id())).append(":TEAM_ID"+vo.getTeam_id());
		return sb.toString();
	}
	
	
	/**
	 * 生成分组Key:登录APP后的加载模板
	 * NEWS_KEY:SCHOOL_ID1030:NEWS_GROUP022005:
	 * 前缀:学校ID:用户类型：团队类型:年级或者楼层:班级或者寝室ID   
	 * @return 唯一Key
	 */
	public static String getNewsDictGroupKey(Integer school_id, String dict_group) {
		StringBuffer sb = new StringBuffer("NEWS_KEY:SCHOOL_ID"+school_id).append(":NEWS_GROUP"+dict_group);
		return sb.toString();
	}
	
	/**
	 * 生成分组Key:登录APP后的加载模板
	 * NEWS_KEY:SCHOOL_ID1030:NEWS_GROUP022005:NEWSID351:
	 * 前缀:学校ID:用户类型：团队类型:年级或者楼层:班级或者寝室ID   
	 * @return 唯一Key
	 */
	public static String getNewsKey(Integer school_id, String dict_group, Integer news_id) {
		StringBuffer sb = new StringBuffer("NEWS_KEY:SCHOOL_ID"+school_id).append(":NEWS_GROUP"+dict_group).append(":NEWS_ID"+news_id);
		return sb.toString();
	}
	
	
	/**
	 * 生成分组Key
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getReadKey(Map<String,Object> map) {
		if (DictConstants.USERTYPE_PARENT.equals(map.get("user_type").toString()))
			map.put("user_type",DictConstants.USERTYPE_STUDENT);
		StringBuffer sb = new StringBuffer("GROUP_KEY:SCHOOL_ID"+ActionUtil.getSchoolID()).append(":USER_TYPE"+map.get("user_type"));
		sb.append(":TEAM_TYPE"+map.get("TEAM_TYPE")).append(":GROUP_ID"+map.get("group_id")).append(":TEAM_ID"+map.get("team_id"));
		return sb.toString();
	}

	
	/**
	 * 生成动态消息的唯一Key
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getDynamicKey(String module_code, Integer module_pdid, String suffix){
		return KEY_DYNAMIC_PRE+KEY_MODULE_CODE+module_code+KEY_MODULE_PKID+module_pdid+":"+suffix;
	}
	
	/**
	 * 生成通知的唯一Key
	 * @param module_code
	 * @param notice_id
	 * @return
	 */
	public static String getNoticeKey(String module_code, Integer notice_id, Integer school_id){
		return KEY_NOTICE_PRE+"SCHOOL_ID"+school_id+":MODULE_CODE"+module_code+KEY_NOTICE_ID+notice_id;
	}
	
	/**
	 * 生成通知接收者唯一KEY
	 * @param user_type
	 * @param school_id
	 * @param user_id
	 * @param student_id
	 * @return
	 */
	public static String getNoticeUnionKey(String module_code, String user_type, Integer school_id, Integer user_id, Integer student_id){
		if (DictConstants.USERTYPE_PARENT.equals(user_type) || DictConstants.USERTYPE_STUDENT.equals(user_type) )
			return KEY_NOTICE_PRE+"SCHOOL_ID"+school_id+":MODULE_CODE"+module_code+":USER_TYPE"+DictConstants.USERTYPE_STUDENT+":STUDENT_ID"+student_id;
			else return KEY_NOTICE_PRE+"SCHOOL_ID"+school_id+":MODULE_CODE"+module_code+":USER_TYPE"+user_type+":USER_ID"+user_id; 
	}
	
	/**
	 * 生成动态消息的唯一Key
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getDynamicKey(String module_code, Long module_pdid, String suffix){
		return KEY_DYNAMIC_PRE+KEY_MODULE_CODE+module_code+KEY_MODULE_PKID+module_pdid+":"+suffix;
	}
	
	
	/**
	 * 获取个人动态集合Key
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getUnionDynamicSetKey(Integer school_id, String user_type, Integer user_id, Integer student_id){
		return KEY_SET_PRE+KEY_DYNAMIC_PRE+ RedisKeyUtil.getUnionKey(school_id,user_type,user_id,student_id);
	}
	
	/**
	 * 获取个人动态集合Key
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getUnionDynamicSetKey(){
		return RedisKeyUtil.getUnionDynamicSetKey(ActionUtil.getSchoolID(),ActionUtil.getUserType(),ActionUtil.getUserID(),ActionUtil.getStudentID());
	}
	
	/**
	 * 获取分组动态集合Key
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getGroupDynamicSetKey(Integer school_id, String team_type, Integer group_id, Integer team_id){
		return KEY_SET_PRE+KEY_DYNAMIC_PRE+ RedisKeyUtil.getGroupKey(school_id,team_type,group_id,team_id);
	}
	
	/**
	 * 获取分组动态集合Key
	 * @author chentian610
	 * @return 唯一Key
	 */
	public static String getGroupDynamicSetKey(ReceiveVO vo){
		return getGroupDynamicSetKey(vo.getSchool_id(),vo.getTeam_type(),vo.getGroup_id(),vo.getTeam_id());
	}
	
	/**
	 * 获取排名key
	 * @param school_id
	 * @param team_type
	 * @param score_type
	 * @return
	 */
	public static String getRankKey(Integer school_id, String team_type, String score_type){
		return KEY_RANK_PRE+"SCHOOL_ID"+school_id+":TEAM_TYPE"+team_type+":SCORE_TYPE"+score_type;
	}
	
	/**
	 * 获取最近记录key
	 */
	public static String getRecordKey(Integer school_id, String team_type, String score_type, String attend_item,
                                      Integer group_id, Integer team_id){
		if (IntegerUtil.isEmpty(group_id)) group_id=0;
		if (DictConstants.TEAM_TYPE_CLASS.equals(team_type) && DictConstants.SCORE_TYPE_ATTEND.equals(score_type)) 
			return KEY_RECORD_PRE + "SCHOOL_ID"+school_id+":TEAM_TYPE"+team_type+":SCORE_TYPE"+score_type+
					":ATTEND_ITEM"+attend_item+":GROUP_ID"+group_id+":TEAM_ID"+team_id;
		else return KEY_RECORD_PRE + "SCHOOL_ID"+school_id+":TEAM_TYPE"+team_type+":SCORE_TYPE"+score_type+
				":GROUP_ID"+group_id+":TEAM_ID"+team_id;
	}

	/**
	 * 获取学校个推key
	 */
	public static String getGetuiKey(Integer school_id){
		return KEY_GETUI_PRE+"SCHOOL_ID"+school_id;
	}
	
	/**
	 * 获取考勤项目，扣分原因信息key
	 */
	public static String getCodeKey(Integer school_id, String code){
		return KEY_CODE_PRE+"SCHOOL_ID"+school_id+":CODE"+code;
	}
	
	/**
	 * 获取班级作业统计的key
	 * @param school_id 学校ID
	 * @param group_id 年级ID
	 * @param team_id 班级ID
	 * @param team_type 班级类型
	 * @param course 科目类型
	 * @return
	 */
	public static String getHomeworkCountKey(Integer school_id, Integer group_id, Integer team_id, String team_type, String course){
		if (IntegerUtil.isEmpty(group_id)) group_id = 0;
		if (StringUtil.isEmpty(course)) return "HOMEWORK:SCHOOL_ID"+school_id+":TEAM_TYPE"+team_type+":GROUP_ID"+group_id+":TEAM_ID"+team_id+"";
		else return "HOMEWORK:SCHOOL_ID"+school_id+":TEAM_TYPE"+team_type+":COURSE"+course+":GROUP_ID"+group_id+":TEAM_ID"+team_id+"";
	}
	/**
	 * 获取使用作业统计的key
	 * @param school_id 学校ID
	 * @return
	 */
	public static String getHomeworkSchoolKey(Integer school_id){
		return "HOMEWORK:SCHOOL_ID"+school_id+"";
	}
	/**
	 * 获取学生作业统计的key
	 * @param school_id 学校ID
	 * @param team_id team_type
	 * @param student_id 学生ID
	 * @param team_type 班级类型
	 * @param course 科目类型
	 * @return
	 */
	public static String getHomeworkStudentCountKey(Integer school_id, Integer group_id, Integer team_id, Integer student_id, String team_type, String course){
		if (StringUtil.isEmpty(course)) return "HOMEWORK:SCHOOL_ID"+school_id+":TEAM_TYPE"+team_type+":GROUP_ID"+group_id+":TEAM_ID"+team_id+":STUDENT_ID"+student_id+"";
		else return "HOMEWORK:SCHOOL_ID"+school_id+":TEAM_TYPE"+team_type+":GROUP_ID"+group_id+":TEAM_ID"+team_id+":COURSE"+course+":STUDENT_ID"+student_id+"";
	}
	/**
	 * 获取首页新闻的key
	 * @param school_id 学校ID
	 * @param news_group 新闻所属的类型
	 * @return
	 */
	public static String getNewsListOfLoginKey(Integer school_id, String news_group){
		return "NEWS_LOGIN_KEY:SCHOOL_ID"+school_id+":NEWS_GROUP"+news_group+"";
	}
	
	/**
	 * 获取作业完成情况的key
	 * @param school_id 学校ID
	 * @param homework_id 作业ID
	 * @param student_id 学生ID
	 * @return
	 */
	public static String getHomeworkKey(Integer school_id, Integer homework_id, Integer student_id){
		if (IntegerUtil.isEmpty(student_id)) return "HOMEWORK_ITEM:SCHOOL_ID"+school_id+":HOMEWORK"+homework_id+"";
		else return "HOMEWORK_ITEM:SCHOOL_ID"+school_id+":HOMEWORK"+homework_id+":STUDENT_ID"+student_id+"";
	}

	//**********以下是扣分的Key*****************//
	public static String getStudentKey(Integer school_id, Integer Student_id){
		return RedisKeyUtil.KEY_SCORE_PRE + "SCHOOL_ID"+school_id+":STUDENT_ID"+Student_id;
	}

	public static String getScoreTeamKey(Integer school_id, String team_type, String score_type, Integer group_id, Integer team_id){
		if (IntegerUtil.isEmpty(group_id)) group_id=0;
		return RedisKeyUtil.KEY_SCORE_PRE + "SCHOOL_ID"+school_id+":TEAM_TYPE"+team_type+":SCORE_TYPE"+score_type+":GROUP_ID"+group_id+":TEAM_ID"+team_id;
	}


	public static String getScoreStudentKeyAttend(Integer school_id, Integer Student_id, String attend_item){
		if (StringUtil.isEmpty(attend_item))
			return  getStudentKey(school_id, Student_id)+":ATTEND_ITEM";
		else
			return getStudentKey(school_id, Student_id)+":ATTEND_ITEM"+attend_item;
	}

	public static String getScoreTeamKeyAttend(Integer school_id, String team_type, String score_type, Integer group_id, Integer team_id, String attend_item){
		if (StringUtil.isEmpty(attend_item))
			return getScoreTeamKey(school_id, team_type,score_type, group_id, team_id)+":ATTEND_ITEM";
		else
			return getScoreTeamKey(school_id, team_type,score_type, group_id, team_id)+":ATTEND_ITEM"+attend_item;
	}

	//学校配置redis key
	public static String getSchoolConfigKey(Integer school_id){
		return RedisKeyUtil.KEY_CONFIG_PRE+"SCHOOL_ID"+school_id;
	}
}
