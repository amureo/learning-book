package com.co.kr.vo;

import java.util.List;

import lombok.Data;

@Data
public class TestingVO {
	private Integer workbook;
	private Integer isRight;
	private Integer current;
	private Integer problem;
	private Integer score;
	private List<Integer> list;
}
