package com.project.lordofrings.repositories;

import com.project.lordofrings.models.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface UserRepository extends CrudRepository<User,Long>{
	public User findByUsername(String username);
	public List<User> findAll();
	
	
	// Example:
	// public YourModelHere findByName(String name);
}
