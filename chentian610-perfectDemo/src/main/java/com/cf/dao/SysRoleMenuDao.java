package com.cf.dao;

import java.util.List;

import com.cf.entity.SysRoleMenuEntity;

/**
 * 角色与菜单对应关系
 * 
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
