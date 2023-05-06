package com.co.kr.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(builderMethodName="builder")
public class SearchDomain {
	private Integer id;
	private Integer workbook;
	private String question;
	private String answer;
	private Integer owner;
}