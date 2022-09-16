package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.CategoryDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
import com.simple.basic.recode.RecodeService;

@Controller
public class RecodeController {

	@Autowired
	RecodeService recodeService;

	@Autowired
	CategoryService categoryService;

	@GetMapping("/recodeInsert") // 업로드 화면
	public String recodeInsert(Model model) {

		List<CategoryDTO> list3 = categoryService.listAll();
		model.addAttribute("list3", list3);
		return "/upload";
	}

	@GetMapping("/recodeList")
	public String recodeList(@RequestParam("cat_name") String cat_name, Model model) {

		List<RecodeDTO> list1 = recodeService.recodeList1();
		List<UploadDTO> list2 = recodeService.recodeList2();
		List<CategoryDTO> list3 = categoryService.listAll();
		
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("cat_name", cat_name);
		return "/recodelist";
	}

	@GetMapping("/recodeDetail")
	public String recodeDetail(@RequestParam("r_num") int r_num, Model model) {
		RecodeDTO dto1 = recodeService.recodeDetail1(r_num);
		UploadDTO dto2 = recodeService.recodeDetail2(r_num);
		List<CategoryDTO> list3 = categoryService.listAll();
		
		model.addAttribute("dto1", dto1);
		model.addAttribute("dto2", dto2);
		model.addAttribute("list3", list3);
		return "/recodeDetail";
	}

	@PostMapping("/recodeDelete")
	public String recodeDelete() {

		return "redirect:/mypage";
	}

	@PostMapping("/recodeUpdate")
	public String recodeUpdate() {

		return "redirect:/mypage";
	}

	@PostMapping("/recodeForm")
	public String recodeForm
	(	@Valid RecodeDTO dto, Errors errors, Model model,
			@RequestParam("r_file1") MultipartFile file,
			@RequestParam("r_image1") MultipartFile image
			){
	    
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "형식 일치");
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			model.addAttribute("dto", dto);
			return "/recodeInsert";
		}
			if(image.getContentType().contains("image") == false) { //이미지파일에 이미지가 아니면
			
			model.addAttribute("dto", dto);
			model.addAttribute("valid_image", "이미지형식만 등록가능합니다");
			return "/recodeInsert";
		}
			
			
		boolean result = recodeService.recodeInsert(image, file, dto); // 정보, 음원, 음원이미지
		return "redirect:/main";
	}
}
