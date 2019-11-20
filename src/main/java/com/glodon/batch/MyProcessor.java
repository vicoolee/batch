package com.glodon.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.glodon.entity.User;


public class MyProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User item) throws Exception {
        if (item.getAge() % 2 == 0) {
            return item;
        }
        return null;
    }
}
