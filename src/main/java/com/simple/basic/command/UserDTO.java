package com.simple.basic.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	@Pattern(message = "아이디는 5자 이상으로 입력해주세요.", regexp = "[a-zA-Z0-9]{5,}")
	private String u_id;
	@Pattern(message = "비밀번호는 6자 이상으로 입력해주세요.", regexp = "[a-zA-Z0-9]{6,}")
	private String u_pw;
	@NotBlank(message = "이메일 형식으로 입력해주세요.")
	private String u_email;
	@Pattern(message = "닉네임은 3자 이상으로 입력해주세요.", regexp = "[ㄱ-ㅎ가-힣a-zA-Z0-9]{3,}")
	private String u_nick;
	@NotNull(message = "유형 선택은 필수입니다.")
	private char u_type;
}
