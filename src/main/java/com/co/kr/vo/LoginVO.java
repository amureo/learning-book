package com.co.kr.vo;

import lombok.Data;

@Data
public class LoginVO {
	private String seq;
	private String id;
	private String pw;
	private String admin;
	private String level;
}