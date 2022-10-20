package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface transactiondao {
	
    int save(Transaction transaction);
	
	int update(Transaction transaction,long id);
	
	int delete(long id);
	
	List<Transaction> getall();
	
	Transaction getbyid(long id);
}
