package com.simple.basic.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.basic.command.CategoryDTO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public List<CategoryDTO> listAll() {
		return categoryMapper.listAll();
	}
}
