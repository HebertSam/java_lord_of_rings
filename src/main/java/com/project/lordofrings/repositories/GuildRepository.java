package com.project.lordofrings.repositories;

import com.project.lordofrings.models.Guild;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface GuildRepository extends CrudRepository<Guild,Long>{
	
}
