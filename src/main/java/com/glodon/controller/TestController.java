package com.glodon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glodon.entity.User;
import com.glodon.service.UserService;

/**
 * @author liwk
 * @date 2019年11月18日 上午11:54:00
 */
@RestController

public class TestController {

	@Autowired
	UserService userservice;

	@RequestMapping(value = "/user/{id}")
	public User show(@PathVariable(name = "id") long id) {
		return userservice.selectUserById(id);
	}
	@RequestMapping("/")
    public String helloworld(){
        return "Hello World 呵呵";
    }
}
