package com.simple.basic.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityDTO {
	private int c_num;
	private String u_id;
	@NotBlank(message = "게시글 제목은 필수입니다.")
	private String c_title;
	@NotBlank(message = "게시글 내용은 필수입니다.")
	private String c_contents;
	private LocalDateTime c_date;
}
