package com.simple.basic.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDTO {
	private int reply_num;
	private int c_num;
	private String u_id;
	private String reply_contents;
	private LocalDateTime reply_date;
}
