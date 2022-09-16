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
public class NoticeDTO {
	private int n_num;
	@NotBlank(message = "공지글 제목은 필수입니다.")
	private String n_title;
	@NotBlank(message = "공지글 내용은 필수입니다.")
	private String n_contents;
	private LocalDateTime n_date;
}
