package com.qingshixun.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingshixun.project.dao.IProfessionDao;
import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.model.Profession;
import com.qingshixun.project.service.IProfessionService;

@Service("professionService")
public class ProfessionServiceImpl implements IProfessionService {

	@Autowired
	private IProfessionDao professionDao;
	@Override
	public Profession load(Integer id) {
		// TODO Auto-generated method stub
		return professionDao.load(id);
	}

	@Override
	public Profession get(Integer id) {
		// TODO Auto-generated method stub
		return professionDao.get(id);
	}

	@Override
	public List<Profession> findAll() {
		// TODO Auto-generated method stub
		return professionDao.findAll();
	}

	@Override
	public void persist(Profession entity) {
		professionDao.persist(entity);

	}

	@Override
	public Integer save(Profession entity) {
		// TODO Auto-generated method stub
		return professionDao.save(entity);
	}

	@Override
	public void saveOrUpdate(Profession entity) {
		// TODO Auto-generated method stub
		professionDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		professionDao.delete(id);
	}

	@Override
	public void flush() {
		professionDao.flush();

	}

	@Override
	public PageBean<Profession> getPageBean(int pageSize, int page) {
		PageBean<Profession> pageBean=new PageBean<>();
		
		int allRows=professionDao.getAllRowCount();
		int totalPage=pageBean.getTotalPages(pageSize, allRows);
		int currentPage=pageBean.getCurPage(page);
		int offset=pageBean.getCurrentPageOffset(pageSize, currentPage);
		List<Profession> list=professionDao.queryByPage( offset, pageSize);
		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

}
