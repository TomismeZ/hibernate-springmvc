package com.qingshixun.project.model;

public class UserBean {
	private Integer id;
	private String name;
	private String password;
	
	
	public UserBean() {
		super();
	}
	public UserBean(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
	}
	
	public UserBean(Integer id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
