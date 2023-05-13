package com.co.kr.domain;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProblemDomain {
	private Integer id;
	private String question;
	private String answer;
	private Integer workbook;
	private Integer ranking;
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
	private Date createAt;
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
	private Date updateAt;
}
