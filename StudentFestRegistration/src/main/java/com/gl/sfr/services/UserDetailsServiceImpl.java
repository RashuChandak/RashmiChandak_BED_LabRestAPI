package com.gl.sfr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gl.sfr.entities.User;
import com.gl.sfr.repositories.UserRepository;
import com.gl.sfr.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService{
   @Autowired
	private UserRepository userRepository;
	
   @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.getUserByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("User not found");
		return new MyUserDetails(user);
	}

}
