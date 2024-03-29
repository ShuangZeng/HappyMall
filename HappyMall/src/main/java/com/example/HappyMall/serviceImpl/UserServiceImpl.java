package com.example.HappyMall.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.User;
import com.example.HappyMall.repository.RoleRepository;
import com.example.HappyMall.repository.UserRepository;
import com.example.HappyMall.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByFullName(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByFullNameAndPassword(username, password);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User saveUser(User user) {
		user.setModifiedDate(new Date());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	@Override
	public User findUserByFullName(String fullName) {
		return userRepository.findByFullName(fullName);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUserByUser(User user) {
		userRepository.deleteById(user.getId());
	}

	@Override
	public User blockUser(User user) {
		Optional<User> userRecordOptional = userRepository.findById(user.getId());
		if (!userRecordOptional.isPresent())
			return null;
		User userRecord = userRecordOptional.get();
		userRecord.setActive_Ind('D');
		userRepository.save(userRecord);
		return user;
	}

	@Override
	public User approveUser(User user) {
		Optional<User> userRecordOptional = userRepository.findById(user.getId());
		if (!userRecordOptional.isPresent())
			return null;
		User userRecord = userRecordOptional.get();
		userRecord.setActive_Ind('A');
		userRepository.save(userRecord);
		return user;
	}
}
