package com.chentian610.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chentian610.user.service.UserService;
import com.chentian610.user.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Reference
    private UserService userService;

    @GetMapping("/")
    public Object index(ModelAndView model,HttpServletRequest request) {
        logger.info("他娘的"+request.getRequestURI());
        model.addObject("now", LocalDateTime.now());
        model.addObject("src", "http://112.124.100.26/file/APP4/school/logo.png");
        model.setViewName("login");
        return model;
    }

    @GetMapping("/login")
    public Object login(ModelAndView model, UserVO userVO) {
        logger.info("登录操作了......");
        userService.loginValid(userVO);
        model.addObject("now", LocalDateTime.now());
        model.addObject("src", "http://112.124.100.26/file/APP4/school/logo.png");
        model.setViewName("index");
        return model;
    }
}
