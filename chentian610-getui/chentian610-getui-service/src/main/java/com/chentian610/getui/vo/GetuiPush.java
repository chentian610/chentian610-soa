package com.chentian610.getui.vo;

import com.alibaba.fastjson.JSON;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.chentian610.getui.GetuiConfig;
import com.chentian610.common.util.ListUtil;
import com.chentian610.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class GetuiPush implements Runnable {
	protected static Logger logger = LoggerFactory.getLogger(GetuiPush.class);
	private GetuiVO getuiVO;
	private IGtPush gtApp;
	private Target target;
	private SingleMessage message;
	private ListMessage list_msg;
	private List<Target> targets;
	private String task_name;

	public GetuiPush(String title,String content,String module_code,String module_pkid,String init_data,Integer user_id){
		initPushData(title, content, module_code, module_pkid, init_data);
		this.target = new Target();
		this.target.setAppId(getuiVO.getApp_id());
		this.target.setAlias(user_id+"");
	}


	/**
	 * 提供IOS消息推送
	 * @param title
	 * @param content
	 * @return
	 */
	private APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String title,String content){
		APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
		if (StringUtil.isEmpty(content) || "null".equals(content)) {
			alertMsg.setBody(title);
		} else {
			alertMsg.setBody(content);
			alertMsg.setTitle(title);
		}
		return alertMsg;
	}

	public GetuiPush(HashMap<String, String> dataMap,List<String> accountList){
		String title = dataMap.get("info_title");
		String content = dataMap.get("info_content");
		String module_code = dataMap.get("module_code");
		String module_pkid = dataMap.get("module_pkid");
		String init_data = JSON.toJSONString(dataMap);
		initPushData(title, content, module_code, module_pkid, init_data);
		this.targets = new ArrayList<Target>();
		for (String alias:accountList) {
			Target target = new Target();
			target.setAppId(getuiVO.getApp_id());
			target.setAlias(alias);
			targets.add(target);
		}
	}


	public GetuiPush(HashMap<String, String> dataMap,HashMap<String, Object> userMap){
		String title = dataMap.get("info_title");
		String content = dataMap.get("info_content");
		String module_code = dataMap.get("module_code");
		String module_pkid = dataMap.get("module_pkid");
		String init_data = JSON.toJSONString(dataMap);
		initPushData(title, content, module_code, module_pkid, init_data);
		this.targets = new ArrayList<Target>();
		Iterator<Entry<String, Object>> iter = userMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			Target target = new Target();
			target.setAppId(getuiVO.getApp_id());
			target.setAlias(entry.getKey().toString());
			targets.add(target);
		}
	}

	/**
	 * 初始化个推配置，同时将推送的标题、内容、关键数据等字段设置到推送列表中
	 * @param title 标题
	 * @param content 内容
	 * @param module_code 模块编码
	 * @param module_pkid 模块唯一ID
	 * @param init_data 关键数据
	 * @author chentian610
	 */
	private void initPushData(String title,String content,String module_code,String module_pkid,String init_data) {
		//获取这个学校的配置
		this.getuiVO = GetuiConfig.getProperty(1030);
		if (this.getuiVO == null) this.getuiVO = GetuiConfig.getProperty(1030);
		this.gtApp = new IGtPush(getuiVO.getApp_key(),getuiVO.getMaster_secret());
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(getuiVO.getApp_id());
		template.setAppkey(getuiVO.getApp_key());
		template.setTransmissionType(2);
		logger.error("xiaoxi tuisong:"+init_data);
		//提供安卓使用，IOS解析出其中的模块编码，主键ID等关键数据，显示在通知列表，同时提供拉起模块页面使用
		template.setTransmissionContent(init_data);
		APNPayload payload = new APNPayload();
		//payload.setBadge(1);
		payload.setContentAvailable(1);
		payload.setAlertMsg(getDictionaryAlertMsg(title,content));
		//提供IOS使用，IOS解析出其中的模块编码，主键ID等关键数据，提供拉起模块页面使用
		payload.addCustomMsg("init_data", init_data);
		template.setAPNInfo(payload);
		this.list_msg = new ListMessage();
		list_msg.setOffline(true);
		list_msg.setOfflineExpireTime(2 * 1000 * 3600);
		list_msg.setData(template);
		this.message = new SingleMessage();
		message.setOffline(true);
		message.setOfflineExpireTime(2 * 1000 * 3600);
		message.setData(template);
		this.task_name = module_code + module_pkid;
	}

	public void run(){
		if (this.target!=null) gtApp.pushMessageToSingle(message,target);
		else {
			String taskId = gtApp.getContentId(list_msg, task_name);
			StringBuffer sb = new StringBuffer();
			for (Target target: targets) sb.append(target.getAlias()+",");
			logger.error("GETUI CONFIG="+ JSON.toJSONString(this.getuiVO));
			logger.error("GETUI:taskId="+taskId+"TARGET_LIST:"+sb.toString());
			//采用递归方式，不能超过100
			while (targets.size()>99) {
				List<Target> sublist = targets.subList(0, 100);
				gtApp.pushMessageToList(taskId, sublist);
				targets.removeAll(sublist);
			}
			if (ListUtil.isNotEmpty(targets)) gtApp.pushMessageToList(taskId, targets);
		}
	}
}
