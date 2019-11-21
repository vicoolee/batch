package com.glodon.batch.writer;

import java.util.Objects;

import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

import com.glodon.entity.Enterprise;
import com.glodon.service.EnterpriseService;


public class CorpClassifier implements Classifier<Enterprise, ItemWriter<? super Enterprise>>{
	
	
	EnterpriseService enterpriseService;
	
	
	private ItemWriter<Enterprise> insertWriter; 
	
	
	private ItemWriter<Enterprise> updateWriter;

	public CorpClassifier(EnterpriseService enterpriseService,ItemWriter<Enterprise> insertWriter,ItemWriter<Enterprise> updateWriter) {
		this.enterpriseService = enterpriseService;
		this.insertWriter = insertWriter;
		this.updateWriter = updateWriter;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ItemWriter<? super Enterprise> classify(Enterprise classifiable) {
		Enterprise ep = enterpriseService.selectEnterpriseById(classifiable.getId());
		if(Objects.isNull(ep)) {
			return insertWriter;
		}else {
			return updateWriter;
		}
		
	}

}
