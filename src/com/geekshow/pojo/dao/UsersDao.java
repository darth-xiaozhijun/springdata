package com.geekshow.pojo.dao;

import java.util.List;

import com.geekshow.pojo.pojo.Users;

public interface UsersDao {
	
	void insertUsers(Users users);
	void updateUsers(Users users);
	void deleteUsers(Users users);
	Users selectUsersById(Integer userid);
	
	/**
	 * HQL
	 * @param username
	 * @return
	 */
	List<Users> selectUserByName(String username);
	
	/**
	 * SQL
	 * @param username
	 * @return
	 */
	List<Users> selectUserByNameUseSQL(String username);
}
