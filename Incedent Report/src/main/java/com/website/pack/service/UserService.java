package com.website.pack.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.pack.model.Role;
import com.website.pack.model.User;
import com.website.pack.repository.UserRepository;



@Service
@Transactional
public class UserService {

	@Autowired
	private RoleService roleService;
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	public List<Role> findUserRole(Role role){
		return userRepository.findByRole(role);
	}


	public List<User> showAllUsers() {
		List<User> users = new ArrayList<User>();
		for (User user : userRepository.findAll()) {
			users.add(user);
		}
		return users;
	}

	public void deleteMyUser(Long id) {
		userRepository.deleteById(id);


	}

	public User editUser(Long id) {
		return userRepository.findOne(id);
	}



	public static String hash256(String data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(data.getBytes());
		return bytesToHex(md.digest());
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (byte byt : bytes)
			result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
		return result.toString();
	}

	public boolean isUserPresent(String email) {
		User u = userRepository.findByEmail(email);
		if(u!=null) {
			return true;
		}
		else {
			return false;
		}

	}

	public List<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameLike("%"+username+"%");
	}

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}


	public void createUser(User user) {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//String password = encoder.encode(user.getPassword());
		try {
			user.setPassword(hash256(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		Role role = new Role();
		role = roleService.findByName("User");
		user.getRoles().add(role);
		
		userRepository.save(user);
	}
	
	public void editMyUser(User user) {
		User cUser = userRepository.findById(user.getUser_id());
		if(user.getEmail()!=null && !user.getEmail().equals("")) {
			cUser.setEmail(user.getEmail());
		}
		if(user.getName()!=null && !user.getName().equals("")) {
			cUser.setName(user.getName());
		}
		if(user.getSurname()!=null && !user.getSurname().equals("")) {
			cUser.setSurname(user.getSurname());
		}
		if(user.getUsername()!=null && !user.getUsername().equals("")) {
			cUser.setUsername(user.getUsername());
		}
		
		userRepository.save(cUser);
	}
	
	public void saveMyUser(User user) {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//String password = encoder.encode(user.getPassword());
		//user.setPassword(password);

		try {
			user.setPassword(hash256(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		userRepository.save(user);
	}

	public User findEmailAndPassword(String email, String password) {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//password = encoder.encode(password);

		try {
			password = hash256(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userRepository.findByEmailAndPassword(email, password);
	}


	public User findByUsernameAndPassword(String username, String password) {
		try {
			password = hash256(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRepository.findByUsernameAndPassword(username, password);
	}



	public User findByID(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}



	public User findByIdAndPassword(int id, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByIdAndPassword(id, password);
	}



	public User findByIdAndNonCriptPassword(int id, String password) {
		try {
			password = hash256(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return userRepository.findByIdAndPassword(id, password);
	}





}
