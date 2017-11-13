package com.qingshixun.project.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "t_user1")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
	// 实体主键（自增长）
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 性别(枚举值)
	public enum Gender {
		男, 女;
	}

	// 用户名称（不可以为空）
	/*@Column(length = 100, nullable = false)*/
	private String name;

	// 用户性别(不可以为空)
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Gender gender = Gender.男;

	// 用户登录密码(不可以为空)
	/*@Column(length = 100, nullable = false)*/
	private String password;

	// 再次输入登录密码
	@Transient // 不作为数据库持久化字段
	private String passwordAgain;

	// 用户邮箱（可以为空）
	@Column(length = 200, nullable = true)
	private String email;

	// 创建时间(updateable=false表示编辑后，不更新此字段)
	@Column(nullable = true, length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE)
	private Date creatTime = new Date(System.currentTimeMillis());

	// 最后更新时间
	@Column(nullable = true, length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE)
	private Date updateTime = new Date(System.currentTimeMillis());

	//出生日期
	private String birth;
	
	//用户头像
	private String avatar;

	@ManyToOne(targetEntity=Profession.class,fetch=FetchType.EAGER)
	private Profession profession;
	
	@ManyToMany(targetEntity=Hobby.class, cascade = {CascadeType.PERSIST}, fetch=FetchType.LAZY)
	@JoinTable(name="t_user_hobby",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={
			@JoinColumn(name="hobby_id")
	})
	private Set<Hobby> hobbies = new HashSet<Hobby>();
	
	
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public Set<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", password=" + password
				+ ", passwordAgain=" + passwordAgain + ", email=" + email + ", creatTime=" + creatTime + ", updateTime="
				+ updateTime + ", birth=" + birth + ", avatar=" + avatar + "]";
	}

	

	
	
}