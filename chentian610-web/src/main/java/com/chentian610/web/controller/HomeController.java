package com.chentian610.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chentian610.framework.BaseController;
import com.chentian610.user.service.UserService;
import com.chentian610.user.vo.UserVO;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
public class HomeController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Reference
    private UserService userService;

    @GetMapping("/")
    public Object index(ModelAndView model,HttpServletRequest request) {
        logger.info("他娘的"+request.getRequestURI());
        model.addObject("now", LocalDateTime.now());
        model.addObject("src", "http://112.124.100.26/file/APP4/school/logo.png");
        UserVO userVO = new UserVO();
        userVO.setUser_name("陈天辉");
        userVO.setPass_word("123456");
        model.addObject("user",userVO);
        model.setViewName("login");
        return model;
    }

    @PostMapping("/")
    public Object login(ModelAndView model, UserVO userVO) {
        logger.info("登录操作了......");
        userService.loginValid(userVO);
        model.addObject("now", LocalDateTime.now());
        model.addObject("src", "http://112.124.100.26/file/APP4/school/logo.png");
        model.setViewName("index");
        return model;
    }

    @GetMapping("/test")
    public Object test(HttpServletRequest request) {
        UserVO userVO = new UserVO();
        userVO.setUser_name("sdfaf");
        userVO.setUser_id(233);
        return userVO;
    }

    @RequestMapping("properties")
    @ResponseBody
    java.util.Properties properties() {
        return System.getProperties();
    }
}
