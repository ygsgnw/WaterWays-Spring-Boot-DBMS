package com.masters.waterways.daos;

import java.util.List;


import com.masters.waterways.models.Users;
import org.apache.catalina.User;

public interface UsersDao {
	int insert (Users users);
	int update (Users users, int id);
	int delete (int id);
	List<Users> getAll ();
	List<Users> getAllNonEmployees ();
	Users getById (int id);

	Users getByEmailId(String emailId);
}
