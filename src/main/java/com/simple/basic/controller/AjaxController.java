package com.simple.basic.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.basic.recode.RecodeService;

@RestController
public class AjaxController {

	@Autowired
	RecodeService recodeService;
	
	@Value("${project.upload.path}")
	String uploadPath;
	
	@GetMapping("/display")
	public byte[] display(@RequestParam("r_image") String r_image) {
		
		String saveImage = uploadPath + "\\" + r_image;
		
		byte[] result = null;
		try {
			File file = new File(saveImage);
			result = FileCopyUtils.copyToByteArray(file);
		}catch(Exception e) {
			System.out.println("이미지 경로 참조 에러");
			e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping("/display2")
	public byte[] display2(@RequestParam("r_file") String r_file) {
		
		String saveMusic = uploadPath + "\\" + r_file;
		
		byte[] result = null;
		try {
			File file = new File(saveMusic);
			result = FileCopyUtils.copyToByteArray(file);
		}catch(Exception e) {
			System.out.println("음악 경로 참조 에러");
			e.printStackTrace();
		}
		
		return result;
	}
}
