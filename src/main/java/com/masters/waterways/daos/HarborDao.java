package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.Harbor;

public interface HarborDao {
	int insert (Harbor harbor);
	int update (Harbor harbor, int id);
	int delete (int id);
	List<Harbor> getAll ();
	Harbor getById (int id);
}
