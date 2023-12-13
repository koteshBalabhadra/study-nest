package com.learnsphere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.entities.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {

	Users findByEmail(String email);

	boolean existsByEmail(String email);

}
