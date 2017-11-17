package com.project.lordofrings.repositories;

import com.project.lordofrings.models.Ring;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface RingRepository extends CrudRepository<Ring,Long>{
	public List<Ring> findAll();
	
	
	// Example:
	// public YourModelHere findByName(String name);
}
