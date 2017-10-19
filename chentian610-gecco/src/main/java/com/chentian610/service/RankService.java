package com.chentian610.service;

import com.github.pagehelper.PageInfo;
import com.chentian610.model.Rank;

import java.util.List;

/**
 * Created by yangqj on 2017/4/21.
 */
public interface RankService extends IService<Rank>{
    PageInfo<Rank> selectByPage(Rank user, int start, int length);

    Rank selectByRank(String username);

    void delRank(Integer userid);

    List<String> getAllBrands();

    List<String> getRanksByBrand(String brand);



}
