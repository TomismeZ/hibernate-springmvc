package com.qingshixun.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingshixun.project.dao.IUserDao;
import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.model.User;
import com.qingshixun.project.model.UserBean;
import com.qingshixun.project.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	IUserDao userDao;

	/**
	 * pageSize为每页显示的记录数
	 * page为当前显示的网页
	 */
	@Override
	public PageBean<User> getPageBean(int pageSize, int page) {
		PageBean<User> pageBean=new PageBean<>();
		String hql="from User order by id desc";
		int allRows=userDao.getAllRowCount(hql);
		int totalPage=pageBean.getTotalPages(pageSize, allRows);
		int currentPage=pageBean.getCurPage(page);
		int offset=pageBean.getCurrentPageOffset(pageSize, currentPage);
		List<User> list=userDao.queryByPage(hql, offset, pageSize);
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public User load(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过id来查找用户
	 */
	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}
	
	/**
	 * 查找所有的用户
	 */
	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	/**
	 * 插入数据到数据库
	 */
	@Override
	public void persist(User entity) {
		userDao.persist(entity);
		
	}
	
	/**
	 * 保存数据到数据库
	 */
	@Override
	public Integer save(User entity) {
		
		return userDao.save(entity);
	}
	
	/**
	 * 保存并更新数据到数据库
	 */
	@Override
	public void saveOrUpdate(User entity) {
		
		userDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
		
	}

	@Override
	public void flush() {
		userDao.flush();
		
	}

	@Override
	public UserBean loginUser(String username, String password) {
		User user = userDao.loginUser(username, password);
		UserBean bean=new UserBean(user);
		return bean;
	}

	@Override
	public User findByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByName(userName);
	}

	@Override
	public void delete(Integer[] ids) {
		// TODO Auto-generated method stub
		userDao.delete(ids);
	}


}
