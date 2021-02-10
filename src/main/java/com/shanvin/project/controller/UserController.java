package com.shanvin.project.controller;

import com.shanvin.project.entity.User;
import com.shanvin.project.mapper.mysql.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger("ApplicationLogger");

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "listuser", method = RequestMethod.GET)
    public List<User> listUser() {
        List<User> userList = userMapper.listUser();
        logger.info("listUser return: {}", userList);
        return userList;
    }

}
