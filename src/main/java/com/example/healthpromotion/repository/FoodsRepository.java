package com.example.healthpromotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.healthpromotion.entity.Foods;

public interface FoodsRepository extends JpaRepository<Foods, Integer>{

}
