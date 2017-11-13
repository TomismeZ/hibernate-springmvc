package com.qingshixun.project.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qingshixun.project.dao.IProfessionDao;
import com.qingshixun.project.model.Profession;

@Repository("professionDao")
public class ProfessionDaoImpl implements IProfessionDao {

	/**
	 * 当设置该属性时，就可以引用该对象，也不需要set注入了
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Profession load(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().load(Profession.class, id);
	}

	@Override
	public Profession get(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().get(Profession.class, id);
	}

	@Override
	public List<Profession> findAll() {
		
		return getCurrentSession().createQuery("from Profession").getResultList();
	}

	@Override
	public void persist(Profession entity) {
		getCurrentSession().persist(entity);

	}

	@Override
	public Integer save(Profession entity) {
		// TODO Auto-generated method stub
		return (Integer) getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Profession entity) {
		getCurrentSession().saveOrUpdate(entity);

	}

	@Override
	public void delete(Integer id) {
		Profession profession = getCurrentSession().get(Profession.class, id);
		getCurrentSession().delete(profession);
	}

	@Override
	public void flush() {
		getCurrentSession().flush();

	}
	@Override
	public List<Profession> queryByPage(int offset, int pageSize) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Profession").setFirstResult(offset).setMaxResults(pageSize).getResultList();
	}
	@Override
	public int getAllRowCount() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Profession").getResultList().size();
	}

}
