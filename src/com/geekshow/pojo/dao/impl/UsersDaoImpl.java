package com.geekshow.pojo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.geekshow.pojo.dao.UsersDao;
import com.geekshow.pojo.pojo.Users;

@Repository
public class UsersDaoImpl  implements UsersDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void insertUsers(Users users) {
		this.hibernateTemplate.save(users);
	}

	@Override
	public void updateUsers(Users users) {
		this.hibernateTemplate.update(users);

	}

	@Override
	public void deleteUsers(Users users) {
		this.hibernateTemplate.delete(users);

	}

	@Override
	public Users selectUsersById(Integer userid) {
		return this.hibernateTemplate.get(Users.class, userid);
	}

	/**
	 * HQL
	 */
	@Override
	public List<Users> selectUserByName(String username) {
		
		//getCurrentSession:当前session必须要有事务边界，且只能处理唯一的一个事务。当事务提交或者回滚后session自动失效
		//openSession:每次都会打开一个新的session.加入每次使用多次。则获得的是不同session对象。使用完毕后我们需要手动的调用colse方法关闭session
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		//sql:select * from t_users where username = 
		Query query = session.createQuery("from Users where username = :abc");
		Query queryTemp = query.setString("abc",username);
		
		return queryTemp.list();
	}
	
	/**
	 * SQL
	 */
	@Override
	public List<Users> selectUserByNameUseSQL(String username) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery("select * from t_users where username = ?").
				addEntity(Users.class).setString(0, username);
		return query.list();
	}

}
