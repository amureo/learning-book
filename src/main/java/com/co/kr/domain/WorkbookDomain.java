package com.co.kr.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WorkbookDomain {
	private Integer id;
	private String title;
	private Integer category;
	private Integer owner;
	private Integer ranking;
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
	private Date createAt;
	@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
	private Date updateAt;
}
