package com.simple.basic.recode;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.RecodeDTO;
import com.simple.basic.command.UploadDTO;
@Mapper
public interface RecodeMapper {
 
	public boolean recodeInsert(RecodeDTO dto);
	public boolean recodeFileInsert(UploadDTO dto);
	public List<RecodeDTO> recodeList1();
	public List<UploadDTO> recodeList2();
	public RecodeDTO recodeDetail1(int r_num);
	public UploadDTO recodeDetail2(int r_num);
	public boolean recodeDelete(int r_num);
	public boolean recodeUpdate(RecodeDTO dto);
}
