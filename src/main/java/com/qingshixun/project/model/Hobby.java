package com.qingshixun.project.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "t_hobby")
public class Hobby {
	// 实体主键（自增长）
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 爱好名称
	@Column(length = 100, nullable = false)
	private String name;

	// 创建时间(updateable=false表示编辑后，不更新此字段)
	@Column(nullable = true, length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE)
	private Date creatTime = new Date(System.currentTimeMillis());
	
	@ManyToMany(targetEntity=User.class,mappedBy="hobbies",fetch=FetchType.LAZY)
	private Set<User> users=new HashSet<>();
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

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
	
	
}
