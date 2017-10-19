package com.chentian610.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.chentian610.mapper.RankMapper;
import com.chentian610.model.Rank;
import com.chentian610.service.RankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangqj on 2017/4/21.
 */
@Service("rankService")
public class RankServiceImpl extends BaseService<Rank> implements RankService {

    @Resource
    private RankMapper rankMapper;

    @Override
    public PageInfo<Rank> selectByPage(Rank rank, int start, int length) {
        int page = start/length+1;
        Example example = new Example(Rank.class);
        Example.Criteria criteria = example.createCriteria();
        if (rank.getId() != null) {
            criteria.andEqualTo("id", rank.getId());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<Rank> rankList = selectByExample(example);
        return new PageInfo<>(rankList);
    }

    @Override
    public Rank selectByRank(String username) {
        return null;
    }

    @Override
    public void delRank(Integer userid) {

    }

    //    @Override
    public Rank selectByrank(String rankname) {
        Example example = new Example(Rank.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("rank",rankname);
        List<Rank> rankList = selectByExample(example);
        if(rankList.size()>0){
            return rankList.get(0);
        }
        return null;
    }

//    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delrank(Integer rankid) {
        //删除用户表
        mapper.deleteByPrimaryKey(rankid);
    }

//    @Override
    public List<String> getAllBrands() {
        return null;
    }

    @Override
    public List<String> getRanksByBrand(String brand) {
        return null;
    }

    //    @Override
    public List<String> getranksByBrand(String brand) {
        return null;
//        return rankMapper.getRanksByBrand(brand);
    }
}
