package com.masters.waterways.daos;

import java.util.List;

import com.masters.waterways.models.Harbor;

public interface HarborDao {
	int insert (Harbor harbor);

	List<Harbor> getAll ();

	Harbor getById (int id);

	int setSuspended(int id);

	int setActive(int id);
}
