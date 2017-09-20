package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.model.Keyword;
import com.study.service.KeywordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * Created by yangqj on 2017/4/21.
 */
@Service("keywordService")
public class KeywordServiceImpl extends BaseService<Keyword> implements KeywordService{

    @Override
    public PageInfo<Keyword> selectByPage(Keyword keyword, int start, int length) {
        int page = start/length+1;
        Example example = new Example(Keyword.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(keyword.getKeyword())) {
            criteria.andLike("keyword", "%" + keyword.getKeyword() + "%");
        }
        if (keyword.getId() != null) {
            criteria.andEqualTo("id", keyword.getId());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<Keyword> keywordList = selectByExample(example);
        return new PageInfo<>(keywordList);
    }

    @Override
    public Keyword selectByKeyword(String keywordname) {
        Example example = new Example(Keyword.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("keyword",keywordname);
        List<Keyword> keywordList = selectByExample(example);
        if(keywordList.size()>0){
            return keywordList.get(0);
        }
            return null;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delKeyword(Integer keywordid) {
        //删除用户表
        mapper.deleteByPrimaryKey(keywordid);
    }
}
