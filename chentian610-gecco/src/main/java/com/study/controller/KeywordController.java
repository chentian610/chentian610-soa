package com.study.controller;

import com.github.pagehelper.PageInfo;
import com.study.model.Keyword;
import com.study.service.KeywordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/22.
 */
@RestController
@RequestMapping("/keywords")
public class KeywordController {
    @Resource
    private KeywordService keywordService;

    @RequestMapping
    public Map<String,Object> getAll(Keyword keyword, String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<Keyword> pageInfo = keywordService.selectByPage(keyword, start, length);
        System.out.println("pageInfo.getTotal():"+pageInfo.getTotal());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }


    @RequestMapping(value = "/add")
    public String add(Keyword keyword) {
        Keyword u = keywordService.selectByKeyword(keyword.getKeyword());
        if(u != null)
            return "error";
        try {
            keywordService.save(keyword);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
      try{
          keywordService.delKeyword(id);
          return "success";
      }catch (Exception e){
          e.printStackTrace();
          return "fail";
      }
    }

}
