package com.masters.waterways.daos;

import java.util.List;


import com.masters.waterways.models.Users;

public interface UsersDao {
	int insert (Users users);
	int update (Users users);
	List<Users> getAll ();
	List<Users> getAllNonEmployees ();
	Users getById (int id);
}
