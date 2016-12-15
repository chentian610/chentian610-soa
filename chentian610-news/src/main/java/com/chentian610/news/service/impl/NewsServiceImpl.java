package com.chentian610.news.service.impl;

import com.chentian610.news.service.NewsService;
import com.chentian610.news.vo.NewsVO;
import com.chentian610.common.util.ActionUtil;
import com.chentian610.common.util.DateUtil;
import com.chentian610.common.util.StringUtil;
import com.chentian610.framework.GeneralDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="NewsServiceImpl")
public class NewsServiceImpl implements NewsService{
	@Autowired
	private GeneralDAO dao;
	/**
	 * 添加校园风采
	 * @param vo
	 */
	public void addNews(NewsVO vo) {
		vo.setSchool_id(ActionUtil.getSchoolID());
		vo.setCreate_by(ActionUtil.getUserID());
		vo.setCreate_date(ActionUtil.getSysTime());
		if (StringUtil.isEmpty(vo.getDept_name()))
			vo.setDept_name(ActionUtil.getParameter("user_name"));
		if (StringUtil.isEmpty(vo.getDeploy_date()))
			vo.setDeploy_date(DateUtil.formatDateToString(ActionUtil.getSysTime(), "yyyy-MM-dd"));
		vo.setNews_id(dao.insertObjectReturnID("newsMap.insertNews", vo));
	}
}
