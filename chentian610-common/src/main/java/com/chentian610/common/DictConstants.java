package com.chentian610.common;

/**
 * 存放数据字典的静态类
 * @author chentian610
 */
public class DictConstants {
	
	/**
	 * 数据字典：是
	 */
	public static final Integer TRUE = 1;
	
	/**
	 * 数据字典：否
	 */
	public static final Integer FALSE = 0;
	
	/**
	 * 数据字典：空值
	 */
	public static final String DICT_NONE = "0";
	
	/**
	 * 数据字典：状态
	 */
	public static final String DICT_STATUS = "001";
	/**
	 * 状态：正常状态
	 */
	public static final String STATUS_NORMAL = "001005";
	/**
	 * 状态：未启用，未激活
	 */
	public static final String STATUS_INACTIVE = "001010";
	/**
	 * 状态：停止、禁用
	 */
	public static final String STATUS_STOP = "001015";
	/**
	 * 数据字典：学校
	 */
	public static final String DICT_SCHOOL = "002";
	/**
	 * 幼儿园
	 */
	public static final String SCHOOL_KINDERGARTEN = "002005";
	/**
	 * 小学
	 */
	public static final String SCHOOL_JUNIOR = "002010";
	/**
	 * 初中
	 */
	public static final String SCHOOL_MIDDLE = "002015";
	/**
	 * 高中
	 */
	public static final String SCHOOL_HIGH = "002020";
	/**
	 * 数据字典：用户类型
	 */
	public static final String USERTYPE_ALL = "003";
	/**
	 * 老师
	 */
	public static final String USERTYPE_TEACHER = "003005";
	/**
	 * 学生
	 */
	public static final String USERTYPE_STUDENT = "003010";
	/**
	 * 家长
	 */
	public static final String USERTYPE_PARENT = "003015";
	
	/**
	 * 学校管理员
	 */
	public static final String USERTYPE_ADMIN = "003020";
	
	/**
	 * 代理商
	 */
	public static final String USERTYPE_AGENT = "003025";
	
	/**
	 * 超级管理员
	 */
	public static final String USERTYPE_SUPER = "003099";
	
	/**
	 * 家长:父亲
	 */
	public static final String USERTYPE_PARENT_FARTHER = "003015005";
	
	/**
	 * 家长:母亲
	 */
	public static final String USERTYPE_PARENT_MOTHER = "003015010";
	
	/**
	 * 家长:爷爷
	 */
	public static final String USERTYPE_PARENT_YEYE = "003015015";
	
	/**
	 * 家长:奶奶
	 */
	public static final String USERTYPE_PARENT_NAINAI = "003015020";
	
	/**
	 * 家长:外公
	 */
	public static final String USERTYPE_PARENT_WAIGONG = "003015025";
	
	/**
	 * 家长:外婆
	 */
	public static final String USERTYPE_PARENT_WAIPO = "003015030";
	
	/**
	 * 家长:其他
	 */
	public static final String USERTYPE_PARENT_OTHER = "003015035";
	
	/**
	 * 班级类型：普通班级，带有年级号、班级号
	 */
	public static final String CLASSTYPE_COMMON = "004005";
	/**
	 * 特殊班级：培训班，没有年级号、班级号
	 */
	public static final String CLASSTYPE_SPECIAL = "004010";
	
	/**
	 *客户端类型：IOS客户端
	 */
	public static final String APPTYPE_IOS = "005005";
	/**
	 * 客户端类型：安卓客户端
	 */
	public static final String APPTYPE_ANDROID = "005010";
	
	/**
	 * 客户端类型：WEB客户端
	 */
	public static final String APPTYPE_WEB = "005015";
	
	/**
	 * 相册类型
	 */
	public static final String PHOTOTYPE = "006";
	
	/**
	 * 相册类型：个人
	 */
	public static final String PHOTOTYPE_PERSON = "006005";
	/**
	 * 相册类型：班级
	 */
	public static final String PHOTOTYPE_CLASS = "006010";
	
	/**
	 * 相册类型：个人模板照片
	 */
	public static final String PHOTOTYPE_TEMPLATE = "006015";
	
	/**
	 * 学校状态：申请
	 */
	public static final String SCH_STATUS_APPLY = "007005";
	/**
	 * 学校状态：审核通过
	 */
	public static final String SCH_STATUS_PASS = "007010";
	
	/**
	 * 学校状态：驳回申请
	 */
	public static final String SCH_STATUS_RETURN = "007015";
	
	/**
	 * 消息状态
	 */
	public static final String INFO_STATUS = "008";
	
	/**
	 * 消息状态：已发送，或者未接收
	 */
	public static final String INFO_STATUS_SEND = "008005";
	/**
	 * 消息状态：已接收
	 */
	public static final String INFO_STATUS_RECEIVED = "008010";
	
	/**
	 * 消息状态：已查看
	 */
	public static final String INFO_STATUS_SEEN = "008015";
	
	
	/**
	 * 消息状态：已回复
	 */
	public static final String INFO_STATUS_REPLAY = "008020";
	
	/**
	 * 模块类型：用户模块
	 */
	public static final String MODULE_CODE_USER="009";
	
	/**
	 * 模块类型：通知
	 */
	public static final String MODULE_CODE_NOTICE="009001";
	
	/**
	 * 模块类型：相册
	 */
	public static final String MODULE_CODE_PHOTO="009002";
	
	/**
	 * 模块类型：作业
	 */
	public static final String MODULE_CODE_HOMEWORK="009003";
	
	/**
	 * 模块类型：校务通知
	 */
	public static final String MODULE_CODE_NOTICE_SCH="009004";
	
	/**
	 * 模块类型:请假条（考勤）教室
	 */
	public static final String MODULE_CODE_ATTEND="009005";
	
	/**
	 * 模块类型：教师网盘
	 */
	public static final String MODULE_CODE_FILE="009006";
	
	/**
	 * 模块类型：课件
	 */
	public static final String MODULE_CODE_NOTE="009007";
	
	/**
	 * 教学课件
	 */
	public static final String MODULE_CODE_COURSEWARE="009010";
	
	/**
	 * 教学视频
	 */
	public static final String MODULE_CODE_VIDEO="009011";
	
	/**
	 * 教师推荐
	 */
	public static final String MODULE_CODE_RECOMMEND="009012";
	
	/**
	 * 模块类型：个人评价
	 */
	public static final String MODULE_CODE_COMMENT="009017";
	
	/**
	 * 模块类型：校园风采
	 */
	public static final String MODULE_CODE_SCHOOLSTYLE="009018";
	
	/**
	 * 模块类型：党员建设
	 */
	public static final String MODULE_CODE_PARTYBUILD="009019";
	
	/**
	 * 模块类型：寝室管理
	 */
	public static final String MODULE_CODE_BEDROOM="009021";
	
	/**
	 * 模块类型：教室管理
	 */
	public static final String MODULE_CODE_CLASSROOM="009023";
	
	/**
	 * 模块类型：通讯录
	 */
	public static final String MODULE_CODE_CONTACT="009024";
	
	/**
	 * 模块类型：在校表现
	 */
	public static final String MODULE_CODE_PERFORMANCE="009025";
	
	/**
	 * 模块类型：校园统计Campus statistics
	 */
	public static final String MODULE_CODE_CAMPUS_STATISTICS="009026";
	
	/**
	 * 模块类型：请假申请
	 */
	public static final String MODULE_CODE_LEAVE="009027";
	
	/**
	 * 消息类型：发送的信息
	 */
	public static final String INFO_TYPE_SEND = "010005";
	
	
	/**
	 * 消息类型：接收的信息
	 */
	public static final String INFO_TYPE_RECEIVE = "010010";
	/**
	 * 团队类型：教室
	 */
	public static final String TEAM_TYPE_CLASS = "011005";
	
	/**
	 * 团队类型：寝室
	 */
	public static final String TEAM_TYPE_BEDROOM = "011010";
	
	/**
	 * 团队类型：兴趣班
	 */
	public static final String TEAM_TYPE_INTEREST  = "011015";
	
	/**
	 * 团队类型：自定义
	 */
	public static final String TEAM_TYPE_DEFINE = "011020";
	/**
	 * 打分类型：纪律
	 */
	public static final String SCORE_TYPE_DISCIPLINE = "012005";
	/**
	 * 打分类型：卫生
	 */
	public static final String SCORE_TYPE_HYGIENE = "012010";
	/**
	 * 打分类型: 考勤
	 */
	public static final String SCORE_TYPE_ATTEND="012015";
	/**
	 * 打分类型：在校表现
	 */
	public static final String SCORE_TYPE_PERFORMANCE="012020";
	
	/**
	 * 考勤项目
	 */
	public static final String ATTEND_ITEM="014";
	
	/**
	 * 考勤项目:早上
	 */
	public static final String ATTEND_ITEM_MORNING="014005";
	
	/**
	 * 考勤项目：下午
	 */
	public static final String ATTEND_ITEM_AFTERNOON="014010";
	
	/**
	 * 考勤项目：晚自习
	 */
	public static final String ATTEND_ITEM_EVENING="014015";
	
	/**
	 * 考勤结果状态:迟到
	 */
	public static final String ATTEND_STATUS_LATE="013041";
	
	/**
	 * 考勤结果状态：未到
	 */
	public static final String ATTEND_STATUS_NONARRIVAL="013043";
	
	/**
	 * 考勤结果状态：请假
	 */
	public static final String ATTEND_STATUS_LEAVE="013042";
	
	/**
	 * DictGroup:兴趣班课程
	 */
	public static final String DICT_INTEREST_COURSE="015045";
	
	/**
	 * DictGroup
	 */
	public static final String DICT_TEACHER="016";
	
	/**
	 * 教师职务: 班主任
	 */
	public static final String DICT_TEACHER_ADVISER="016030";
	
	/**
	 * 教师职务:任课教师
	 */
	public static final String DICT_TEACHER_CLASS="016005";
	
	/**
	 * 教师职务：年级主任
	 */
	public static final String DICT_TEACHER_GRADER="016010";
	
	/**
	 * 教师职务：行政管理
	 */
	public static final String DICT_TEACHER_ADMIN ="016015";
	
	/**
	 * 教师职务：校长（校领导）
	 * 校领导
	 */
	public static final String DICT_TEACHER_MASTER ="016020";
	
	/**
	 * 教师职务：兴趣班教师
	 */
	public static final String DICT_TEACHER_INTEREST="016025";
	
	/**
	 * 附件类型：图片
	 */
	public static final String FILE_TYPE_PICTURE="020005";
	
	/**
	 * 附件类型：音频
	 */
	public static final String FILE_TYPE_SOUND="020010";
	
	/**
	 * 附件类型：视频
	 */
	public static final String FILE_TYPE_VIEW="020015";
		
	/**
	 * 消息类型：APP系统消息
	 */
	public static final String INFO_TYPE_SYSTEM="021005";
	
	/**
	 * 消息类型：本地模块消息
	 */
	public static final String INFO_TYPE_LOCAL="021010";
	
	/**
	 * 消息类型：URL消息
	 */
	public static final String INFO_TYPE_URL="021015";
	
	/**
	 * 文章分組
	 */
	public static final String DCIT_GROUP_NEWS="022";
	
	/**
	 * 党员建设分类：党建快讯
	 */
	public static final String PARTY_BUILD_NEWS="022005";
	
	/**
	 * 党员建设分类：政策资料
	 */
	public static final String PARTY_BUILD_DATA="022010";
	
	/**
	 * 党员建设分类：党员活动
	 */
	public static final String PARTY_BUILD_ACTIVITY="022015";
	
	/**
	 * 消息显示类型：默认全部显示 
	 */
	public static final String SHOW_TYPE_DEFAULT="023";
	
	/**
	 * 消息显示类型：只显示在APP首页
	 */
	public static final String SHOW_TYPE_SCREEN="023005";
	
	/**
	 * 消息显示类型：只显示在 所有动态中
	 */
	public static final String SHOW_TYPE_ALLINFO="023010";
	
	/**
	 * 动态或者消息推送链接类型：模块级别,(默认)(index.html)
	 */
	public static final String LINK_TYPE_DEFUALT="024005";
	
	/**
	 * 动态或者消息推送链接类型：详情页面 (detail.html)
	 */
	public static final String LINK_TYPE_DETAIL="024010";
	
	/**
	 * 动态或者消息推送链接类型：回复页面 (conve.html)
	 */
	public static final String LINK_TYPE_REPLY="024015";
	
	/**
	 * 动态或者消息推送链接类型：其他页面
	 */
	public static final String LINK_TYPE_OTHER="024020";
	
	/**
	 * 报表统计类型：按天
	 */
	public static final String SUM_TYPE_DAY="027005";
	
	/**
	 * 报表统计类型：按周
	 */
	public static final String SUM_TYPE_WEEK="027010";
	
	/**
	 * 报表统计类型：按月
	 */
	public static final String SUM_TYPE_MONTH="027015";
	
	/**
	 * 报表统计类型：按学期
	 */
	public static final String SUM_TYPE_TERM="027025";
	
	/**
	 * 报表统计类型：按年
	 */
	public static final String SUM_TYPE_YEAR="027020";
	
	/**
	 * 报表统计对象：统计到学生
	 */
	public static final String COUNT_TYPE_STUDENT="028005";
	
	/**
	 * 报表统计对象：统计到班级/寝室
	 */
	public static final String COUNT_TYPE_TEAM="028010";
	
	/**
	 * 手机号码类型：异常手机号码
	 */
	public static final String PHONE_TYPE_ERROR="-1";
	
	/**
	 * 手机号码类型：中国移动
	 */
	public static final String PHONE_TYPE_CMCC="1";
	
	/**
	 * 手机号码类型：中国联通
	 */
	public static final String PHONE_TYPE_CUCC="2";
	
	/**
	 * 手机号码类型：中国电信
	 */
	public static final String PHONE_TYPE_CTC="3";
	
	/**
	 *  党员建设
	 */
	public static final String NEWS_DYJS_DICT_GROUP = "022010";
	
	/**
	 * 学校风采
	 */
	public static final String NEWS_XXFC_DICT_GROUP = "022005";
	
	/**
	 *  校园新闻
	 */
	public static final String NEWS_XYXW_DICT_GROUP = "022015";
	
	/**
	 *  校友之窗
	 */
	public static final String NEWS_XYZC_DICT_GROUP = "022020";
	
	/**
	 *  招生招聘
	 */
	public static final String NEWS_ZSZP_DICT_GROUP = "022025";
	
	/**
	 *  学校风采:校长致辞
	 */
	public static final String NEWS_XYFC_XZZC_DICT_CODE = "025045";
	
	/**
	 *  学校风采 ：学校简介
	 */
	public static final String NEWS_XYFC_XYJJ_DICT_CODE = "025005";
	
	/**
	 * 底部汇总函数：汇总
	 */
	public static final String TABLE_SUM="031005";
	
	/**
	 * 新闻模块：是/否，缩略默认为0(不缩略)
	 */
	public static final String NEWS_CONTENT_IS_RESIZE = "1";
	
	public static final String DICT_GROUP_NEWS_CSS = "032";
	
	/**
	 * 请假申请
	 */
	public static final String LEAVE_STATUS = "034";
	
	/**
	 * 新建请假申请
	 */
	public static final String LEAVE_STATUS_NEW = "034005";
	
	/**
	 * 发送请假申请给教导主任
	 */
	public static final String LEAVE_STATUS_APPEOVER = "034010";
	
	/**
	 * 发送请假申请给校领导
	 */
	public static final String LEAVE_STATUS_MASTER = "034015";
	
	/**
	 * 通过请假申请
	 */
	public static final String LEAVE_STATUS_PASS = "034020";
	
	/**
	 * 撤回（删除）请假申请
	 */
	public static final String LEAVE_STATUS_CANCEL = "034025";
	
	/**
	 * 驳回请假申请
	 */
	public static final String LEAVE_STATUS_RECALL = "034030";

	/**
	 * 新闻模板
	 */
	public static final String NEWS_TEMPLATE_TYPE ="035";
	/**
	 * 新闻模板:新闻(带图片)
	 */
	public static final String NEWS_TEMPLATE_TYPE_A_MAP ="035005";
	/**
	 * 新闻模板:新闻(不带图片)
	 */
	public static final String NEWS_TEMPLATE_TYPE_NO_PICTURE ="035010";
	/**
	 * 新闻模板:红头文件
	 */
	public static final String NEWS_TEMPLATE_TYPE_RED_HEAD_DOCUMENT ="035015";
	/**
	 * 新闻模板:新闻(活泼带图片)
	 */
	public static final String NEWS_TEMPLATE_TYPE_LIVELY_PICTURE ="035020";
	/**
	 * 新闻模板：自定义
	 */
	public static final String NEWS_TEMPLATE_TYPE_DEFINE = "035025";
}
