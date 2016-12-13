package com.chentian610.chentian610.news.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chentian610.chentian610.getui.service.GetuiService;
import com.chentian610.chentian610.news.service.NewsService;
import com.chentian610.chentian610.news.vo.NewsVO;
import com.chentian610.common.DictConstants;
import com.chentian610.common.util.ActionUtil;
import com.chentian610.common.util.StringUtil;
import com.chentian610.common.vo.ReceiveVO;
import com.chentian610.common.vo.annotation.PutCache;
import com.chentian610.framework.BaseController;
import com.chentian610.framework.ResultField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
//@EnableAutoConfiguration
@RequestMapping(value="newsAction")
public class NewsController extends BaseController{
	@Autowired
	private NewsService newsService;
	@Reference
	private GetuiService getuiService;

	/**
	 * 添加新闻信息并发送动态信息和推送
	 * @param vo
	 */
	@PutCache(name="newsList",value="school_id,news_code222")
	@RequestMapping(value="/addNews")
	@ResultField(includes={"news_id","title","content","main_pic_url","dept_name","deploy_date","module_code","dict_group","template_type"})
	public @ResponseBody Object addNews(NewsVO vo){
		vo.setDict_group(ActionUtil.getParameter("dict_group"));
		newsService.addNews(vo);
//		newsService.addInformation(vo);
		HashMap<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("module_code",DictConstants.MODULE_CODE_SCHOOLSTYLE);
		//生成module_pkid
		dataMap.put("module_pkid",vo.getNews_id().toString());
		dataMap.put("link_type", DictConstants.LINK_TYPE_DETAIL);
		dataMap.put("info_url", "detail.html");
		dataMap.put("info_date", ActionUtil.getSysTime().getTime()+"");
		String user_type = DictConstants.NEWS_DYJS_DICT_GROUP.equals(vo.getDict_group())?DictConstants.USERTYPE_TEACHER:DictConstants.USERTYPE_ALL;
		dataMap.put("user_type", user_type);
		dataMap.put("info_title",StringUtil.subString(vo.getTitle(),60));
		dataMap.put("info_content",StringUtil.subString(vo.getContent_text(),60));
		dataMap.put("news_code",vo.getNews_code());
		dataMap.put("dict_group", vo.getDict_group());
		dataMap.put("school_id",ActionUtil.getSchoolID()+"");
		List<ReceiveVO> receiveList = new ArrayList<ReceiveVO>();
		ReceiveVO receive = new ReceiveVO(ActionUtil.getSchoolID(),DictConstants.TEAM_TYPE_CLASS,0,0);
		receiveList.add(receive);
		//对相应用户分组进行推送
		getuiService.pushMessage(dataMap,ActionUtil.getUserID());
		return vo;
	}
	
}
