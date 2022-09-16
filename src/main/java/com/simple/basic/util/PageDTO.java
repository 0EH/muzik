package com.simple.basic.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Data;

@Data
public class PageDTO {
	
	//criteria클래스를 받아서 화면에 그려질 페이지네이션을 계산
	private int start; //페이지네이션 시작번호
	private int end;   //페이지네이션 끝번호
	private boolean prev; //페이지네이션 이전버튼
 	private boolean next; //페이지네이션 다음버튼
	
	private int total; //전체게시글수
	private int page;  //조회하는 페이지번호(cri)
	private int amount; //조회하는 데이터개수(cri)
	private int realEnd; //실제 끝번호
	private Criteria cri; //페이지 기준클래스
	
	private List<Integer> pageList; 
	
	

	public PageDTO(Criteria cri, int total) {
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;

		
		this.end = (int)(Math.ceil( this.page / 10.0) ) * 10;
		

		this.start = this.end - 10 + 1;

		this.realEnd = (int)(Math.ceil( this.total / (double)this.amount ));
		

		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		

		this.prev = this.start > 1;
		

		this.next = this.realEnd > this.end;
		

		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
		
		
	}
	
	
	
}
