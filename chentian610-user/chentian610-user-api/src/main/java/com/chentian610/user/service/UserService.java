package com.chentian610.user.service;

import com.chentian610.user.vo.UserVO;

public interface UserService {
	/**
	 * 登录验证
	 */
	public void loginValid(UserVO userVO);
}
