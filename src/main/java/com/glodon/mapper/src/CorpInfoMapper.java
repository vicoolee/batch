package com.glodon.mapper.src;

import com.glodon.entity.CorpInfo;

public interface CorpInfoMapper {
    int insert(CorpInfo record);

    int insertSelective(CorpInfo record);
}