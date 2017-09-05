package com.chentian610.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.chentian610.framework.GeneralDAO;
import com.chentian610.getui.service.GetuiService;
import com.chentian610.user.exception.UserExcepiton;
import com.chentian610.user.service.UserService;
import com.chentian610.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.zookeeper.Watcher;

import java.util.Date;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private GeneralDAO dao;

	@Reference
	private GetuiService getuiService;

	/**
	 * 登录验证
	 *
	 */
	@Override
	public void loginValid(UserVO userVO) {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("info_title","测试标题");
		String string = "共产党太多第一代领导第一代领导第一代领导第一代领导的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
				+ "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
				+ "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
		map.put("info_content","测试内容");
		map.put("module_code","009001");
		map.put("module_pkid","9999");
		map.put("info_content",string);
		getuiService.pushMessage(map,1265);
		if (dao.queryObject("userMap.getUserList",userVO)==null)
			throw new UserExcepiton("账户或者密码错误...");
	}
}
