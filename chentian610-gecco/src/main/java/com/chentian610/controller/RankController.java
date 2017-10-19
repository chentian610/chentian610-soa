package com.chentian610.controller;

import com.github.pagehelper.PageInfo;
import com.chentian610.model.Rank;
import com.chentian610.service.RankService;
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
@RequestMapping("/ranks")
public class RankController {
    @Resource
    private RankService rankService;

    @RequestMapping
    public Map<String,Object> getAll(Rank rank, String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<Rank> pageInfo = rankService.selectByPage(rank, start, length);
        System.out.println("pageInfo.getTotal():"+pageInfo.getTotal());
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }


    @RequestMapping(value = "/add")
    public String add(Rank rank) {
        Rank u = rankService.selectByRank("");
        if(u != null)
            return "error";
        try {
            rankService.save(rank);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
      try{
          rankService.delRank(id);
          return "success";
      }catch (Exception e){
          e.printStackTrace();
          return "fail";
      }
    }

}
