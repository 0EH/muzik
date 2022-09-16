package com.simple.basic.community;

import java.util.List;

import com.simple.basic.command.CommunityDTO;
import com.simple.basic.util.Criteria;

public interface CommunityService {

	public boolean communityRegist(CommunityDTO dto); //등록
	
	public List<CommunityDTO> getList(Criteria cri); //페이징 조회
	public List<CommunityDTO> getMyPost(String u_id);
	
	public int getTotal(Criteria cri); //전체게시글수
	
	public CommunityDTO getDetail(int c_num); //상세조회
	
	public boolean communityUpdate(CommunityDTO dto); //수정
	public boolean communityDelete(int c_num); //삭제
}
