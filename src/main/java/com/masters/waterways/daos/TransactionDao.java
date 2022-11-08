package com.masters.waterways.daos;

import java.util.List;
import com.masters.waterways.models.*;


public interface TransactionDao {
    int insert (Transaction transaction);
	List<Transaction> getAll ();
	Transaction getById (int id);
}
