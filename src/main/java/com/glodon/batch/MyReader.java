package com.glodon.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.core.io.ClassPathResource;

import com.glodon.entity.User;

public class MyReader extends FlatFileItemReader<User> {
    public MyReader(){
        createReader();
    }

    private void createReader(){
        this.setResource(new ClassPathResource("User.txt"));
        this.setLinesToSkip(1);
        this.setLineMapper(userLineMapper());
    }

    private LineMapper<User> userLineMapper(){
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(userLineTokenizer());
        lineMapper.setFieldSetMapper(new UserFieldStepMapper());
        lineMapper.afterPropertiesSet(); 
        return lineMapper;
    }

    private LineTokenizer userLineTokenizer(){
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"ID", "NAME", "AGE"});
        return tokenizer;
    }

    private static class UserFieldStepMapper implements FieldSetMapper<User>{
        @Override
        public User mapFieldSet(FieldSet fieldSet) throws BindException {
            User u = new User(fieldSet.readLong("ID"), 
                    fieldSet.readString("NAME"), 
                    fieldSet.readInt("AGE"));
            return u;
        }

    }
}

