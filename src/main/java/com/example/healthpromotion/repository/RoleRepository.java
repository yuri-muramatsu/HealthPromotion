package com.example.healthpromotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.healthpromotion.entity.Role;

public interface  RoleRepository extends JpaRepository<Role, Integer>{
	public Role findByName(String name);
}
