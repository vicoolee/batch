package com.glodon.batch.writer;

import java.util.Objects;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.stereotype.Component;

import com.glodon.entity.Enterprise;
import com.glodon.mapper.dest.EnterpriseMapper;
import com.glodon.service.EnterpriseService;

@Component
public class CorpClassifier implements Classifier<Enterprise, ItemWriter<? super Enterprise>>{
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@Autowired
    private EnterpriseMapper enterpriseMapper;
	

	
	
	@Autowired
	@Qualifier("insertEnterpriseWriter")
	private ItemWriter<Enterprise> insertWriter; 
	
	@Autowired
	@Qualifier("updateEnterpriseWriter")
	private ItemWriter<Enterprise> updateWriter;

	public CorpClassifier() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ItemWriter<? super Enterprise> classify(Enterprise classifiable) {
		Enterprise ep = enterpriseMapper.selectEnterpriseById(classifiable.getId());
		if(Objects.isNull(ep)) {
			return insertWriter;
		}else {
			return updateWriter;
		}
		
	}

}
