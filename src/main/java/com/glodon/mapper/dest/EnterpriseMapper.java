package com.glodon.mapper.dest;

import com.glodon.entity.Enterprise;

public interface EnterpriseMapper {
    int insert(Enterprise record);
    int update(Enterprise record);
    int insertSelective(Enterprise record);
    
    Enterprise selectEnterpriseById(String id);
}