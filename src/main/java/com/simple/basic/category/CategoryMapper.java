package com.simple.basic.category;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.CategoryDTO;

@Mapper
public interface CategoryMapper {
	public List<CategoryDTO> listAll(); 
}
