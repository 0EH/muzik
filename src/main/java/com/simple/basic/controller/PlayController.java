package com.simple.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.CategoryDTO;
import com.simple.basic.play.PlayService;

@Controller
public class PlayController {

	@Autowired
	PlayService playService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("playlist")
	public String playlist(Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		model.addAttribute("list3", list3);
		return "playlist";
	}
}
