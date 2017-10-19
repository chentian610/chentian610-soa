package com.chentian610.mapper;

import com.chentian610.model.Role;
import com.chentian610.util.MyMapper;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    public List<Role> queryRoleListWithSelected(Integer id);
}