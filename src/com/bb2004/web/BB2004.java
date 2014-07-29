package com.bb2004.web;

import java.util.List;
import java.util.Map;

public class BB2004 {
	private String userName;
	private int age;
	private float height;
	private List mistress;
	private Map dog;
	private BB2004 wife;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public List getMistress() {
		return mistress;
	}

	public void setMistress(List mistress) {
		this.mistress = mistress;
	}

	public Map getDog() {
		return dog;
	}

	public void setDog(Map dog) {
		this.dog = dog;
	}

	public BB2004 getWife() {
		return wife;
	}

	public void setWife(BB2004 wife) {
		this.wife = wife;
	}

	public void say(){
		userName ="2222";
	}
}