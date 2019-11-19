package com.glodon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glodon.entity.User;
import com.glodon.mapper.src.UserMapper;
import com.glodon.service.UserService;


/** 
* @author liwk
* @date 2019年11月18日 上午11:55:46 
*/
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserMapper userMapper;
	

	
	@Override
	public User selectUserById(Long id) {
		User u = userMapper.selectByPrimaryKey(id);
		
		return userMapper.selectByPrimaryKey(id);
	}

}
