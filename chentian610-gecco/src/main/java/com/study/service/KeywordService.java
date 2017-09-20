package com.study.service;

import com.github.pagehelper.PageInfo;
import com.study.model.Keyword;

/**
 * Created by yangqj on 2017/4/21.
 */
public interface KeywordService extends IService<Keyword>{
    PageInfo<Keyword> selectByPage(Keyword user, int start, int length);

    Keyword selectByKeyword(String username);

    void delKeyword(Integer userid);

}
