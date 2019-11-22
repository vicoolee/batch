package com.glodon.batch.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;

/**
* @author liwk-b
* @version 创建时间 ：2019年11月22日 下午3:49:53
* @ClassName 类名称
* @Description 类描述o
*/
public class MulitMybatisItemReader<T> extends AbstractItemStreamItemReader<T> {
	
	private ItemReader<T> currentDelegate;
	
	private List<ItemReader<T>> delegates;//1,2
	
	private int currentIndex;
	
	@Override
	public T read() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
		// TODO Auto-generated method stub
		T item = null;
	    if(null != currentDelegate) {
	      item = currentDelegate.read();
	      if(null == item) {
	        this.currentDelegate = null;
	      }
	    }
	    // Move to next delegate if previous was exhausted!
	    if(null == item && this.currentIndex < this.delegates.size()) {
	      this.currentDelegate = this.delegates.get(this.currentIndex++);
	   // Recurse to read() to simulate loop through delegates
	      item = read();
	    }
	    return item;
	}
	
	
	public ItemReader<T> getCurrentDelegate() {
		return currentDelegate;
	}
	public void setCurrentDelegate(ItemReader<T> currentDelegate) {
		this.currentDelegate = currentDelegate;
	}
	public List<ItemReader<T>> getDelegates() {
		return delegates;
	}
	public void setDelegates(List<ItemReader<T>> delegates) {
		this.delegates = delegates;
	}
}
