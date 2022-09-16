package com.simple.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.basic.category.CategoryService;
import com.simple.basic.command.CategoryDTO;
import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
import com.simple.basic.command.UserDTO;
import com.simple.basic.user.UserService;


@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping("/login")
	public String userLogin() {
		
		return "login";
	}
	
	@GetMapping("/signUp")
	public String userInsert() {
		
		return "register";
	}
	
	@GetMapping("/logout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	
	@GetMapping("/mypage")
	public String userMypage() {
		
		return "mypage";
	}
	
	@GetMapping("/userInfo")
	public String userInfo() {
		
		return "userInfo";
	}
	
	@GetMapping("/artistList")
	public String artistList(Model model) {
		List<UserDTO> art= userService.artistList();
		List<CategoryDTO> list3 = categoryService.listAll();
		
		model.addAttribute("art", art);
		model.addAttribute("list3", list3);
		return "artistList";
	}
	
	@GetMapping("/artistDetail")
	public String artistDetail(@RequestParam("u_id")String u_id, @RequestParam("u_nick")String u_nick, Model model) {
		List<CategoryDTO> list3 = categoryService.listAll();
		List<RecodeDTO> list1 = userService.myRecode1(u_id);
	    List<UploadDTO> list2 = userService.myRecode2();

	    model.addAttribute("list1", list1);
	    model.addAttribute("list2", list2);
	    model.addAttribute("u_id", u_id);
	    model.addAttribute("u_nick", u_nick);
		model.addAttribute("list3", list3);
		return "artistDetail";
	}
	
	@GetMapping("/followList")
	public String followList() {
		
		return "followList";
	}
	
	@PostMapping("/login")
	public String loginForm(UserDTO dto, HttpServletRequest request, RedirectAttributes rttr, Model model) {
		HttpSession session = request.getSession();
		UserDTO user = userService.login(dto);
		
		if(user == null) {
			session.setAttribute("user", null);
			model.addAttribute("msg", "아이디, 비밀번호가 일치하지 않습니다.");
			return "login";
		}
		else {
			session.setAttribute("user", user);
		}
		
		//VO를 가지고 로그인 SQL 실행
		//로그인 성공이라 가정
				
		return "redirect:/main";
	}
	
	@PostMapping("/mypageForm")
	public String mypageForm(@RequestParam("u_id1") String u_id, Model model) {
		List<RecodeDTO> list1 = userService.myRecode1(u_id);
		List<UploadDTO> list2 = userService.myRecode2();
		List<CategoryDTO> list3 = categoryService.listAll();
		
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		return "/mypage";
	}
	
	@PostMapping("/userUpdate")
	public String userUpdate(HttpServletRequest request, UserDTO dto) {
		HttpSession session = request.getSession();
		boolean b = userService.userUpdate(dto);
		UserDTO user = dto;
		session.setAttribute("user", user);
		
		return "redirect:/main";
	}
	
	@PostMapping("/userDelete")
	public String userDelete(@RequestParam("u_id") String u_id, RedirectAttributes ra, HttpSession session) {
		boolean b = userService.userDelete(u_id);
		if(b) {
			ra.addFlashAttribute("msg", "수정이 완료되었습니다.");
		}else {
			ra.addFlashAttribute("msg", "오류로 인해 수정이 실패되었습니다.");
		}
		session.invalidate();
		return "redirect:/main";
	}
	
	@PostMapping("/userForm")
	public String userForm(@Valid UserDTO dto, Errors errors, Model model, String u_id, String u_nick) {
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for (FieldError err : list) {
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "유형 선택은 필수입니다.");
				}
				else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			
			// 다시 회원가입 화면으로
			model.addAttribute("dto", dto);
			return "register";
		}
		int idResult = userService.idCheck(u_id);
		int nickResult = userService.nickCheck(u_nick);
		
		if(idResult == 1 || nickResult == 1) {
			return "register";
		}
		boolean b = userService.userInsert(dto);
		return "redirect:/main";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(String u_id) {
		int result = userService.idCheck(u_id);
		return result;
	}
	
	@PostMapping("/nickCheck")
	@ResponseBody
	public int nickCheck(String u_nick) {
		int result = userService.nickCheck(u_nick);
		return result;
	}
}
