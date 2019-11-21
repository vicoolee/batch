package com.glodon.batch.processor;

import java.util.Date;
import java.util.Objects;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glodon.entity.CorpInfo;
import com.glodon.entity.Enterprise;
import com.glodon.service.EnterpriseService;

@Component
public class CorpProcessor implements ItemProcessor<CorpInfo, Enterprise> {
	
	
	
	@Override
	public Enterprise process(CorpInfo item) throws Exception {
		

		Enterprise ep =  new Enterprise(item.getCorpguid(),item.getCorpname(),item.getLicensenum(),item.getLegalman(),item.getLegalmanidcard(),
				String.valueOf(item.getProvincenum()),String.valueOf(item.getCitynum()),String.valueOf(item.getCountynum()));
		ep.setDataSource(1);
		Date date = new Date();
		ep.setCreateTime(date);
		ep.setUpdateTime(date);
		ep.setCreateBy("DATA_SYNC");
		ep.setUpdateBy("DATA_SYNC");
		return ep;
		
		
		
	}

	

}
