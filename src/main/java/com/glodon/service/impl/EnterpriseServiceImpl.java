package com.glodon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glodon.entity.Enterprise;
import com.glodon.mapper.dest.EnterpriseMapper;
import com.glodon.service.EnterpriseService;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
	
	
	@Autowired
    private EnterpriseMapper enterpriseMapper;
	@Override
	public Enterprise selectEnterpriseById(String id) {
		return enterpriseMapper.selectEnterpriseById(id);
	}

}
