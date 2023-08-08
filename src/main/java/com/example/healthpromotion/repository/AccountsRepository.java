package com.example.healthpromotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.healthpromotion.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Integer>{
    public Accounts findByEmail(String Email);
}
