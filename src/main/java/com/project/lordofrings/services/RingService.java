package com.project.lordofrings.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.lordofrings.models.Guild;
import com.project.lordofrings.models.Ring;
import com.project.lordofrings.repositories.GuildRepository;
import com.project.lordofrings.repositories.RingRepository;

@Service
public class RingService {
	// Member variables / service initializations go here
	private RingRepository ringRepository;
	private GuildRepository guildRepository;
		
	public RingService(RingRepository ringRepository, GuildRepository guildRepository){
		this.ringRepository = ringRepository;
		this.guildRepository = guildRepository;
	}

	public List<Ring> findAll(){
		return ringRepository.findAll();
	}
	public void createRing(Ring ring){
		ringRepository.save(ring);
	}
	public void updateRing(Ring ring){
		ringRepository.save(ring);
	}
	public Ring getRingById(long id){
		return ringRepository.findOne(id);
	}
	public List<Guild> getAllGuilds(){
		return (List<Guild>) guildRepository.findAll();
	}
	public void createGuild(Guild guild){
		guildRepository.save(guild);
	}
	public Guild findbyId(long id){
		return guildRepository.findOne(id);
	}
	public void updateGuild(Guild guild){
		guildRepository.save(guild);
	}
	
	// Crud methods to act on services go here.
}
