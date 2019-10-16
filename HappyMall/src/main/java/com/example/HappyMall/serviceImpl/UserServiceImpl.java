package com.example.HappyMall.serviceImpl;

import java.util.Arrays;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HappyMall.domain.Role;
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
    public UserServiceImpl(UserRepository userRepository,
                       RoleRepository roleRepository,
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
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setActive(1);
//        Role userRole = roleRepository.findByRoleName("admin");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		user.setActive_Ind('P');
		Role userRole = roleRepository.findByRole("ADMIN");
        //user.setRole(new Role());
        return userRepository.save(user);
	}

	@Override
	public User findUserByFullName(String fullName) {
		// TODO Auto-generated method stub
		return userRepository.findByFullName(fullName);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void deleteUserByUser(User user) {
		// TODO Auto-generated method stub
//		userRepository.delete(user);
		userRepository.deleteById(user.getId());
	}



	
}

