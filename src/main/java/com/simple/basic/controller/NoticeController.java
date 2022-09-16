package com.simple.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.simple.basic.notice.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;
}
