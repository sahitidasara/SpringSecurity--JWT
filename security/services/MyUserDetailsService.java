package com.example.carrentalproject.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.carrentalproject.models.User;
import com.example.carrentalproject.models.UserPrincipal;
import com.example.carrentalproject.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).get();
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new UserPrincipal(user);
	}
}
