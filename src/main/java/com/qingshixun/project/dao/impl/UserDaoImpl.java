package com.qingshixun.project.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qingshixun.project.dao.IUserDao;
import com.qingshixun.project.model.User;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	/**
	 * 当设置该属性时，就可以引用该对象，也不需要set注入了
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	 /**
     * 使用hibernate提供的分页功能，得到分页显示的数据
     */
	@Override
	public List<User> queryByPage(String hql, int offset, int pageSize) {
		List<User> list=null;
		try {
			
			list=getCurrentSession().createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			list=null;
		}finally{
			if(getCurrentSession()!=null&&getCurrentSession().isOpen()){
				getCurrentSession().close();
			}
			
		}
		return list;
	}
	

	/**
	 * 通过hql语句得到数据库中记录总数
	 */
	@Override
	public int getAllRowCount(String hql) {
		Integer allRows=0;
		allRows = getCurrentSession().createQuery(hql).getResultList().size();
		return allRows;
	}

	@Override
	public User load(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().load(User.class, id);
	}

	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return getCurrentSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		
		return getCurrentSession().createQuery("from User").getResultList();
	}

	@Override
	public void persist(User entity) {
		getCurrentSession().persist(entity);
		
	}

	@Override
	public Integer save(User entity) {
		
		return (Integer) getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(User entity) {
		getCurrentSession().saveOrUpdate(entity);
		
	}

	@Override
	public void delete(Integer id) {
		User User = getCurrentSession().get(User.class, id);
		if(User!=null){
			getCurrentSession().delete(User);
		}
		
		
	}

	@Override
	public void flush() {
		getCurrentSession().flush();
		
	}

	@Override
	public User loginUser(String username, String password) {
//		Query query = getCurrentSession().createSQLQuery("select id,name,password FROM t_user1 WHERE name=? AND password=?");
//		query.setParameter(0, username);
//		query.setParameter(1, password);
//		/*getCurrentSession().createCriteria(User.class);*/
//		return (UserBean) query.getSingleResult();
		
		Query query =getCurrentSession().createQuery("FROM User WHERE name=? AND password=?");
		query.setParameter(0, username);
		query.setParameter(1, password);
		User user=(User) query.getSingleResult();
		return user;
		
	}

	@Override
	public User findByName(String userName) {
		
		return (User) getCurrentSession().createQuery("FROM User WHERE name=?").setParameter(0, userName).getSingleResult();
	}
	
	/**
	 * 删除多条记录
	 * @param ids
	 */
	@Override
	public void delete(Integer[] userIds) {
		String hql="delete from User where id IN (:idd)";
		getCurrentSession().createQuery(hql).setParameterList("idd",userIds).executeUpdate();
		
	}

}
