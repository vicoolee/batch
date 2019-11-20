package com.glodon.batch;



import org.springframework.batch.item.ItemProcessor;

import com.glodon.entity.Teacher;
import com.glodon.entity.User;

public class Mysql2MysqlProcessor implements ItemProcessor<User, Teacher>{

	@Override
	public Teacher process(User item) throws Exception {
		if (item.getAge() % 2 == 1) {
            return 
            		new Teacher(item.getId(),item.getName(),item.getAge());
        }
		return null;
	}

	

}
