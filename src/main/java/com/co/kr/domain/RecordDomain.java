package com.co.kr.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RecordDomain {
	private Integer id;
	private Integer workbook;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
	private Date createAt;
	private Integer owner;
	private String title;
	private Integer categoryId;
	private String category;
	private String createAtStr;
}
