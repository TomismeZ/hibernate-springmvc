package com.qingshixun.project.dao;

import java.util.List;

import com.qingshixun.project.model.Hobby;
public interface IHobbyDao extends BaseDao<Hobby, Integer>{
	public List<Hobby> queryByPage(int offset, int pageSize);

	/**
	 * 通过hql语句得到数据库中记录总数
	 */
	public int getAllRowCount();
}
