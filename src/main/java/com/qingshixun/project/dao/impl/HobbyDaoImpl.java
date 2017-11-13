package com.qingshixun.project.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qingshixun.project.dao.IHobbyDao;
import com.qingshixun.project.model.Hobby;

@Repository("hobbyDao")
public class HobbyDaoImpl implements IHobbyDao {

	/**
	 * 当设置该属性时，就可以引用该对象，也不需要set注入了
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Hobby load(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().load(Hobby.class, id);
	}

	@Override
	public Hobby get(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().get(Hobby.class, id);
	}

	@Override
	public List<Hobby> findAll() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Hobby").getResultList();
	}

	@Override
	public void persist(Hobby entity) {
		// TODO Auto-generated method stub
		getCurrentSession().persist(entity);
	}

	@Override
	public Integer save(Hobby entity) {
		// TODO Auto-generated method stub
		return (Integer) getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Hobby entity) {
		// TODO Auto-generated method stub
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Hobby hobby = getCurrentSession().get(Hobby.class, id);
		getCurrentSession().delete(hobby);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		getCurrentSession().flush();
	}

	@Override
	public List<Hobby> queryByPage(int offset, int pageSize) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Hobby").setFirstResult(offset).setMaxResults(pageSize).getResultList();
	}

	@Override
	public int getAllRowCount() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Hobby").getResultList().size();
	}

}
