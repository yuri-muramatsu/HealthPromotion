package com.example.healthpromotion.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.healthpromotion.entity.Foods;
import com.example.healthpromotion.repository.FoodsRepository;

@Controller
@RequestMapping("/admin/foods")
public class AdminFoodsController {
	private final FoodsRepository foodsRepository;

	public AdminFoodsController(final FoodsRepository foodsRepository) {
		this.foodsRepository = foodsRepository;
	}

	@GetMapping
	public String index(final Model model) {
		final List<Foods> foods = foodsRepository.findAll();
		model.addAttribute("foods", foods);

		return "admin/foods/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") final Integer id, final Model model) {
		final Foods foods = foodsRepository.getReferenceById(id);

		model.addAttribute("foods", foods);

		return "/admin/foods/show";
	}

}
