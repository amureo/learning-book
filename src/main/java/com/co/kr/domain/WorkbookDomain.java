package com.co.kr.domain;

import lombok.Data;

@Data
public class WorkbookDomain {
	private Integer id;
	private String title;
	private Integer category;
	private Integer owner;
}
