package com.simple.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.simple.basic.reply.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	ReplyService replyService;
}
