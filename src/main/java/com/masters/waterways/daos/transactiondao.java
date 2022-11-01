package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface transactiondao {
	
    int save(Transaction transaction);
	
	int update(Transaction transaction,int id);
	
	int delete(int id);
	
	List<Transaction> getall();
	
	Transaction getbyid(int id);
}
