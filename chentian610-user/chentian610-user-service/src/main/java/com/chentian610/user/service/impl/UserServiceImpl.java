package com.chentian610.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chentian610.framework.GeneralDAO;
import com.chentian610.user.exception.UserExcepiton;
import com.chentian610.user.service.UserService;
import com.chentian610.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private GeneralDAO dao;

	/**
	 * 登录验证
	 *
	 */
	@Override
	public void loginValid(UserVO userVO) {
		if (dao.queryObject("userMap.getUserList",userVO)==null)
			throw new UserExcepiton("账户或者密码错误...");
	}
}
