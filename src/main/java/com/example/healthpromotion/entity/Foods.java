package com.example.healthpromotion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "foods")
@Data
public class Foods {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "dish_type")
	private String dishType;

	@Column(name = "energy")
	private Integer energy;

	@Column(name = "protein")
	private Integer protein;

	@Column(name = "fat")
	private Integer fat;

	@Column(name = "carbohydrate")
	private Integer cardohydrate;

	@Column(name = "fiber")
	private Integer fiber;

	@Column(name = "nacl")
	private Integer nacl;
}
