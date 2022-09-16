package com.simple.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.CategoryDTO;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories")
	public String categories(Model model) {
		List<CategoryDTO> list = categoryService.listAll();
		
		model.addAttribute("list", list);
		return "categories";
	}
}
