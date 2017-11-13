package com.qingshixun.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qingshixun.project.dao.IHobbyDao;
import com.qingshixun.project.model.Hobby;
import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.service.IHobbyService;

@Service("hobbyService")
public class HobbyServiceImpl implements IHobbyService {

	@Autowired
	private IHobbyDao hobbyDao;

	@Override
	public Hobby load(Integer id) {
		// TODO Auto-generated method stub
		return hobbyDao.load(id);
	}

	@Override
	public Hobby get(Integer id) {
		// TODO Auto-generated method stub
		return hobbyDao.get(id);
	}

	@Override
	public List<Hobby> findAll() {
		// TODO Auto-generated method stub
		return hobbyDao.findAll();
	}

	@Override
	public void persist(Hobby entity) {
		// TODO Auto-generated method stub
		hobbyDao.persist(entity);
	}

	@Override
	public Integer save(Hobby entity) {
		// TODO Auto-generated method stub
		return hobbyDao.save(entity);
	}

	@Override
	public void saveOrUpdate(Hobby entity) {
		// TODO Auto-generated method stub
		hobbyDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		hobbyDao.delete(id);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		hobbyDao.flush();
	}

	@Override
	public PageBean<Hobby> getPageBean(int pageSize, int page) {
		PageBean<Hobby> pageBean = new PageBean<>();

		int allRows = hobbyDao.getAllRowCount();
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		int currentPage = pageBean.getCurPage(page);
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		List<Hobby> list = hobbyDao.queryByPage(offset, pageSize);
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

}
