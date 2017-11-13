package com.qingshixun.project.dao;

import java.util.List;

import com.qingshixun.project.model.User;

public interface IUserDao extends BaseDao<User, Integer>{
	
	public User loginUser(String username,String password);
	public List<User> queryByPage(String hql, int offset, int pageSize);

	/**
	 * 通过hql语句得到数据库中记录总数
	 */
	public int getAllRowCount(String hql);
	
	public User findByName(String userName);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer[] userIds);
}
