package com.co.kr.domain;

import lombok.Data;

@Data
public class ProblemDomain {
	private Integer id;
	private String question;
	private String answer;
	private Integer workbook;
}
