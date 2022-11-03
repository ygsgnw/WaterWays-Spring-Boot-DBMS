package com.masters.waterways.daos;

import java.util.List;


import com.masters.waterways.models.Users;

public interface UsersDao {
	int insert (Users users);
	int update (Users users, int id);
	int delete (int id);
	List<Users> getAll ();
	Users getById (int id);
}
