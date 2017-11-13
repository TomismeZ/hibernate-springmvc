package com.qingshixun.project.service;

import com.qingshixun.project.dao.BaseDao;
import com.qingshixun.project.model.Hobby;
import com.qingshixun.project.model.PageBean;

public interface IHobbyService extends BaseDao<Hobby, Integer>{
	/**
	 * 
	 * @param pageSize为每页显示的记录数
	 * @param page为当前显示的网页
	 * @return 返回一个PageBean对象
	 */
	public PageBean<Hobby> getPageBean(int pageSize, int page);
}
