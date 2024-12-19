package com.example.carrentalproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carrentalproject.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@SuppressWarnings("unchecked")
	public User save(User user);
	public List<User> findAll();
	public User findById(Long id);
	public void deleteById(Long id);
	public Optional<User> findByUsername(String username);
}
