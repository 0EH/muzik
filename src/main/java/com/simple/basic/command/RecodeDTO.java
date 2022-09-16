package com.simple.basic.command;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecodeDTO {

	private int r_num;
	private String u_id;
	private String r_file;
	@NotBlank(message = "제목은 필수입니다.")
	private String r_name;
	private String r_describe;
	private String r_image;
	private double r_rate;
	private String r_open;
	private String r_genre;
}
