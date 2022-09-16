package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadDTO {

	
	private Integer upload_no; //pk
	private String r_path;
	private String r_file;
	private String r_image;
	private Integer r_num; //fk
	private String r_name; //fk
}
