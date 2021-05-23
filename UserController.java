package com.iktpreobuka.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.projectNew.entities.UserEntity;

@RestController
@RequestMapping("/projectNew/users")
public class UserController {




	List<UserEntity> users = new ArrayList<UserEntity>();

	protected List<UserEntity> getDB(){
			UserEntity u1=new UserEntity(123, "Vladimir", "Dimitrieski", "vd","seventeen", "vd@gmail.com", Roles.ROLE_CUSTOMER);
			UserEntity u2=new UserEntity(234, "Nebojsa", "Horvat", "nh","eighteen" , "nh@gmail.com", Roles.ROLE_ADMIN);
			UserEntity u3=new UserEntity(345, "Milan", "Celikovic", "mc","nineteen" , "mc@gmail.com", Roles.ROLE_SELLER);
			users.add(u1);
			users.add(u2);
			users.add(u3);
			return users;
		}
	
	//TODO GET 
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public List<UserEntity> getAll(){
		return getDB();
	}
	
	
	//TODO GET  /users/{id}
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public UserEntity getOne(@PathVariable Integer id){
		for (UserEntity ue: getDB()) {
			if(ue.getId().equals(id))
				return ue;
		}
		return null;

	}
	//TODO POST napravi jednog usera - project/users
	@RequestMapping(method=RequestMethod.POST, value="/")
	public UserEntity createUser(@RequestBody UserEntity ue){
		List<UserEntity>users=getDB();
		ue.setId((new Random()).nextInt());
		users.add(ue);
		return ue;		
		}
	
	
	//TODO PUT izmeni korisnika - //{id}
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public UserEntity changeUser(@PathVariable Integer id, @RequestBody UserEntity ue1) {
		List<UserEntity>users=getDB();
		for (UserEntity ue:users) {
		if(ue.getId().equals(id)) {
			if (ue1.getEmail()!=null)
			ue.setEmail(ue1.getEmail());
			if (ue1.getFirstName()!=null)
			ue.setFirstName(ue1.getFirstName());
			if (ue1.getLastName()!=null)
			ue.setLastName(ue1.getLastName());
			if (ue1.getUsername()!=null)
				ue.setUsername(ue1.getUsername());
			
			return ue;
		}
			
		}
		return null;
	}
	
	//TODO PUT /project/users/change/{ role/{role}
		@RequestMapping(value = "/change/{id}/role/{role}", method=RequestMethod.PUT)
		public UserEntity changeUserRole(@PathVariable Integer id, @PathVariable Roles role) {
			List<UserEntity>users=getDB();
			for (UserEntity ue:users) {
			if(ue.getId().equals(id)) {
					ue.setUserRole(role);
				
				return ue;
			}
				
			}
			return null;
		}
	
	
	
	
	
	
	
	
	//TODO PUT /project/users/change/changePassword/{id}
				@RequestMapping(value = "/change/changePassword/{id}", method=RequestMethod.PUT)
				public UserEntity changePassword(@PathVariable Integer id, @RequestParam String password, @RequestParam String newPassword) {
					List<UserEntity>users=getDB();
					for (UserEntity ue:users) {
						if(ue.getId().equals(id)) {
							if(ue.getPassword().equalsIgnoreCase(password))
								ue.setPassword(newPassword);
							return ue;
					}
					}	
					return null;
				}	
				
				
//TODO DELETE 1.9 kreirati REST endpoint koji omogućava brisanje postojećeg korisnika putanja /project/users/{id}
				@RequestMapping(method=RequestMethod.DELETE, value = "/{id}")
				public UserEntity deleteUser(@PathVariable Integer id) {
					List<UserEntity>users=getDB();
					Iterator<UserEntity> it=users.iterator();
					while(it.hasNext()) {
						UserEntity ue=it.next();
						if(ue.getId().equals(id)) {
							it.remove();
							return ue;
						}
					}
					return null;
				}
				
// TODO get 1.10 kreirati REST endpoint koji vraća korisnika po vrednosti prosleđenog usernamea putanja /project/users/by-username/{username}
//u slučaju da ne postoji korisnik sa traženim usernameom vratiti null
				@RequestMapping(value = "/by-username/{username}", method=RequestMethod.GET)
				public UserEntity getByUsername(@PathVariable String username){
					List<UserEntity>users=getDB();
					for (UserEntity ue: users) {
						if(ue.getUsername().equalsIgnoreCase(username))
							return ue;
					}
				
					return null;
	

				}		
					
			}		


