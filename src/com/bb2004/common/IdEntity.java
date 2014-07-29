package com.bb2004.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass//用在实体的继承过程中的父类上�? 
public abstract class IdEntity{
	//@SearchableId//使用注解@Searchable定义ProductInfo跟lucene中的document进行映射 

   protected Long id;
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)//主键生成策略�?,射到你这个类中的 id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
   
}
