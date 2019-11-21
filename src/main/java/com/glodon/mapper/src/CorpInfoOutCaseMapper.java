package com.glodon.mapper.src;

import com.glodon.entity.CorpInfoOutCase;

public interface CorpInfoOutCaseMapper {
    int insert(CorpInfoOutCase record);

    int insertSelective(CorpInfoOutCase record);
}