package com.chentian610.service;

import com.github.pagehelper.PageInfo;
import com.chentian610.model.Keyword;

import java.util.List;

/**
 * Created by yangqj on 2017/4/21.
 */
public interface KeywordService extends IService<Keyword>{
    PageInfo<Keyword> selectByPage(Keyword user, int start, int length);

    Keyword selectByKeyword(String username);

    void delKeyword(Integer userid);

    List<String> getAllBrands();

    List<String> getKeywordsByBrand(String brand);



}
