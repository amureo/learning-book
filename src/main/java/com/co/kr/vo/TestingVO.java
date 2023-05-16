package com.co.kr.vo;

import java.util.List;

import lombok.Data;

@Data
public class TestingVO {
	private Integer workbook;
	private Integer problemId;
	private Integer total;

	private Boolean isRight;
	private Boolean isRand;
	private Integer count;
	private List<Integer> problemIdList;
}
