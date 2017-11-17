package com.project.lordofrings.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Size;


import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User{
	@Id
	@GeneratedValue
	private long id;
	@Size(min=2, max=255)
	private String username;
	@Size(min=2, max=255)
	private String password;
	@Transient
	private String passwordConfirm;
	@Size(min=2, max=255)
	private String firstName;
	@Size(min=2, max=255)
	private String lastName;

	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(
		name="users_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roles;

	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Ring> rings;

	@ManyToMany(mappedBy="users", fetch=FetchType.LAZY)
	private List<Guild> guilds;

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
	public void setUsername(String username){
		this.username = username;
	}
	public String getusername(){
		return username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	public void setPasswordConfirm(String passwordConfirm){
		this.passwordConfirm = passwordConfirm;
	}
	public String getPasswordConfirm(){
		return passwordConfirm;
	}
	public void setRole(Role roles){
		this.roles.add(roles);
	}
	public void setRoleList(List<Role> roles){
		this.roles = roles;
	}
	public List<Role> getRoles(){
		return roles;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setRing(List<Ring> rings){
		this.rings = rings;
	}
	public List<Ring> getRing(){
		if(this.rings == null){
			this.rings = new ArrayList<Ring>();
		}
		return rings;
	}
	public List<Guild> getGuilds(){
		if(this.guilds == null){
			this.guilds = new ArrayList<Guild>();
		}
		return guilds;
	}
	public void setGuild(List<Guild> guilds){
		this.guilds = guilds;
	}
	
	// Setters and Getters go here

	public User(){}
	
	public User(String username, String password, String passwordConfirm){
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
}
