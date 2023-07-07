package com.example.demo.tools;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;


public class ApacheBeanUtils extends BeanUtilsBean{

	@Override
	public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		if(value == null || value.toString().isEmpty()){
			System.out.println("Apache beanUtils");
			return;
		}
		super.copyProperty(bean, name, value);
	}
}
