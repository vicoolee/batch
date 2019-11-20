package com.glodon.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.glodon.entity.User;

public class MyWriter implements ItemWriter<User> {
    @Override
    public void write(List<? extends User> items) throws Exception {
        for(User user : items){
            System.out.println(user);
        }
    }
}

