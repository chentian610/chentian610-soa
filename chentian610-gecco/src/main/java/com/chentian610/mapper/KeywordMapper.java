package com.chentian610.mapper;

import com.chentian610.model.Keyword;
import com.chentian610.util.MyMapper;

import java.util.List;

public interface KeywordMapper extends MyMapper<Keyword> {

    public List<String> getAllBrands();

    public List<String> getKeywordsByBrand(String keyword);
}