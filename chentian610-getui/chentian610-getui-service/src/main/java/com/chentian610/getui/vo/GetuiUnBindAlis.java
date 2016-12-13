package com.chentian610.chentian610.getui.vo;

import com.gexin.rp.sdk.http.IGtPush;
import com.chentian610.chentian610.getui.GetuiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetuiUnBindAlis implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(GetuiUnBindAlis.class);
	private GetuiVO getuiVO;
	private IGtPush gtApp;
	private Integer user_id;
	private String client_id;
	
	public GetuiUnBindAlis(Integer school_id,Integer user_id,String client_id){
		//获得该学校的个推配置
		this.getuiVO = GetuiConfig.getProperty(school_id);
		if (this.getuiVO == null) this.getuiVO = GetuiConfig.getProperty(1030);
		this.gtApp = new IGtPush(getuiVO.getApp_key(),getuiVO.getMaster_secret());
		this.user_id = user_id;
		this.client_id = client_id;
	}
	
	
	public void run(){
		gtApp.unBindAlias(getuiVO.getApp_id(), user_id.toString(), client_id);
		logger.error("user_id = "+user_id+",client_id = "+client_id);
	}
}
