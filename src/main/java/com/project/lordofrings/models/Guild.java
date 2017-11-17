package com.project.lordofrings.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Guild{
	@Id
	@GeneratedValue
	private long id;

	@Size(min=2, max=255)
	private String name;

	private int size;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="guilds_users",
		joinColumns = @JoinColumn(name="guild_id"),
		inverseJoinColumns = @JoinColumn(name="user_id")
	)
	private List<User> users;

	// Member variables and annotations go here.
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setSize(int size){
		this.size = size;
	}
	public int getSize(){
		return size;
	}
	public void setUsers(List<User> users){
		this.users = users;
	}
	public List<User> getUsers(){
		return users;
	}
	
	// Setters and Getters go here

	public Guild(){}
	
	public Guild(String name, int size){
		this.name = name;
		this.size = size;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
}
