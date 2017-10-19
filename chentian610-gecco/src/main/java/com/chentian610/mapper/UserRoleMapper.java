package com.chentian610.mapper;

import com.chentian610.model.UserRole;
import com.chentian610.util.MyMapper;

import java.util.List;

public interface UserRoleMapper extends MyMapper<UserRole> {
    public List<Integer> findUserIdByRoleId(Integer roleId);
}