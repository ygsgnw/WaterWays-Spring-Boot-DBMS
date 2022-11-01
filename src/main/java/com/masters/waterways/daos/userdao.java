package com.masters.waterways.daos;

import java.util.List;


import com.masters.waterways.models.Users;

public interface userdao {

	int save(Users users);
	
	int update(Users users,int id);
	
	int delete(int id);
	
	List<Users> getall();
	
	Users getbyid(int id);
	
}
