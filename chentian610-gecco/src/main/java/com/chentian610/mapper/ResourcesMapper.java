package com.chentian610.mapper;

import com.chentian610.model.Resources;
import com.chentian610.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface ResourcesMapper extends MyMapper<Resources> {

    public List<Resources> queryAll();

    public List<Resources> loadUserResources(Map<String,Object> map);

    public List<Resources> queryResourcesListWithSelected(Integer rid);
}