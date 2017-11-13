package com.qingshixun.project.dao;

import java.util.List;

import com.qingshixun.project.model.Profession;

public interface IProfessionDao extends BaseDao<Profession, Integer>{
	public List<Profession> queryByPage(int offset, int pageSize);

	/**
	 * 通过hql语句得到数据库中记录总数
	 */
	public int getAllRowCount();
}
