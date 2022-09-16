package com.simple.basic.play;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayServiceImpl implements PlayService {

	@Autowired
	PlayMapper playMapper;
}
