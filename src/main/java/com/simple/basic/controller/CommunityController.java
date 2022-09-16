package com.simple.basic.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.CategoryDTO;
import com.simple.basic.command.CommunityDTO;
import com.simple.basic.community.CommunityService;
import com.simple.basic.util.Criteria;
import com.simple.basic.util.PageDTO;

@Controller
public class CommunityController {

	@Autowired
	CommunityService communityService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/communityRegist")
	public String commuInsert(Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		
		model.addAttribute("list3", list3);
		return "communityRegist";
	}
	
	@GetMapping("/communityList")
	public String commuList(Model model, @ModelAttribute("cri") Criteria cri) {
		List<CategoryDTO> list3 = categoryService.listAll();
		List<CommunityDTO> list = communityService.getList(cri); //데이터
		int total = communityService.getTotal(cri); //전체게시글수
		PageDTO pageDTO = new PageDTO(cri, total); //페이지네이션
		
		model.addAttribute("list", list);
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("list3", list3);
		return "communityList";
	}
	
	@GetMapping("/communityDetail")
	public String commuDetail(@RequestParam("c_num") int c_num, Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		CommunityDTO dto = communityService.getDetail(c_num);
		
		model.addAttribute("CommunityDTO", dto);
		model.addAttribute("list3", list3);
		return "communityDetail";
	}
	
	@PostMapping("/communityDelete")
	public String commuDelete(@RequestParam("c_num") int c_num, RedirectAttributes RA) {
		boolean result = communityService.communityDelete(c_num);
		if(result) {
			RA.addFlashAttribute("msg", "삭제 되었습니다");
		} else {
			RA.addFlashAttribute("msg", "삭제에 실패했습니다");
		}	
		return "redirect:/communityList";
	}
	
	@PostMapping("/communityUpdate")
	public String commuUpdate(CommunityDTO dto, RedirectAttributes RA) {
		boolean result = communityService.communityUpdate(dto);
			if(result) {
				RA.addFlashAttribute("msg", "정상 수정 되었습니다");
			} else {
				RA.addFlashAttribute("msg", "정보 수정에 실패했습니다");
			}
		return "redirect:/communityList";
	}
	
	@PostMapping("/communityForm")
	public String commuForm(CommunityDTO dto,Model model) {
		boolean result = communityService.communityRegist(dto);
		return "redirect:/communityList";
	}
	
	@GetMapping("/communityMypost")
	public String commuMypost(@RequestParam("u_id") String u_id, Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		List<CommunityDTO> list = communityService.getMyPost(u_id);
		
		model.addAttribute("list", list);
		model.addAttribute("list3", list3);
		return "communityMypost";
	}
}
