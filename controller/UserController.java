package com.example.carrentalproject.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrentalproject.models.Role;
import com.example.carrentalproject.models.User;
import com.example.carrentalproject.repository.RoleRepository;
import com.example.carrentalproject.repository.UserRepository;
import com.example.carrentalproject.request.CreateUserRequest;
import com.example.carrentalproject.request.UpdateUserRequest;
import com.example.carrentalproject.security.jwt.JwtRequest;
import com.example.carrentalproject.security.jwt.JwtResponse;
import com.example.carrentalproject.security.jwt.JwtService;
import com.example.carrentalproject.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody JwtRequest request) throws BadRequestException {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if (authentication.isAuthenticated()) 
		{
			User authenticatedUser = userRepository.findByUsername(request.getUsername()).orElseThrow();
			String token = jwtService.generateToken(authenticatedUser);
			JwtResponse jwtResponse = new JwtResponse();
			jwtResponse.setToken(token);
			jwtResponse.setExpiresIn(jwtService.getExpirationTime());
			return ResponseEntity.ok(jwtResponse);
		}
		throw new BadRequestException("Authentication failed");
	}
	@PostMapping("/signup")
	public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest userReq) throws BadRequestException {
		User userRes = new User();
		Set<Role> roles = userReq.getRoles();
		if (roles == null) {
			Set<Role> roles2 = new HashSet<Role>();
			roles2.add(getUserRole().get());
			userReq.setRoles(roles2);
		}
		modelMapper.map(userReq, userRes);
		userRes = userService.addUser(userRes);
		return new ResponseEntity<>(userRes, HttpStatus.CREATED);
	}

	@GetMapping("/users/all")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}


}
