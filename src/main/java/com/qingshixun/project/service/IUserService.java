package com.qingshixun.project.service;

import java.util.List;

import com.qingshixun.project.dao.BaseDao;
import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.model.User;
import com.qingshixun.project.model.UserBean;

public interface IUserService extends BaseDao<User, Integer>{

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public UserBean loginUser(String username,String password);
	/**
	 * 
	 * @param pageSize为每页显示的记录数
	 * @param page为当前显示的网页
	 * @return 返回一个PageBean对象
	 */
	public PageBean<User> getPageBean(int pageSize, int page);
	
	public User findByName(String userName);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer[] ids);
}
