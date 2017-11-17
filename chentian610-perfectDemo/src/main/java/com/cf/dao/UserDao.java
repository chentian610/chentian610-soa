package com.cf.dao;

import com.cf.entity.UserEntity;

/**
 * 用户
 * 
 */
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}
