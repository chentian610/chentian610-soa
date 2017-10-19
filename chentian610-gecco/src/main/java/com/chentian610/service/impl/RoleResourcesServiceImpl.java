package com.chentian610.service.impl;

import com.chentian610.model.RoleResources;
import com.chentian610.service.RoleResourcesService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by yangqj on 2017/4/26.
 */
@Service("roleResourcesService")
public class RoleResourcesServiceImpl extends BaseService<RoleResources> implements RoleResourcesService {
   /* @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private ShiroService shiroService;*/


    @Override
    //更新权限
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    @CacheEvict(cacheNames="resources", allEntries=true)
    public void addRoleResources(RoleResources roleResources) {
        //删除
        Example example = new Example(RoleResources.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleid",roleResources.getRoleid());
        mapper.deleteByExample(example);
        //添加
        if(!StringUtils.isEmpty(roleResources.getResourcesid())){
            String[] resourcesArr = roleResources.getResourcesid().split(",");
            for(String resourcesId:resourcesArr ){
                RoleResources r = new RoleResources();
                r.setRoleid(roleResources.getRoleid());
                r.setResourcesid(resourcesId);
                mapper.insert(r);
            }
        }

        //List<Integer> userIds= userRoleMapper.findUserIdByRoleId(roleResources.getRoleid());
        //更新当前登录的用户的权限缓存
        //shiroService.clearUserAuthByUserId(userIds);


    }
}
