package com.co.kr.vo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.co.kr.sort.ProblemSort;
import com.co.kr.sort.TestingView;

import lombok.Data;

@Data
public class TestingVO {
	private Integer workbook;
	private Integer problemId;
	private Integer total;

	private Boolean isRight;
	private Boolean isRand;
	private Integer count;
	private String view;
	private List<Integer> problemIdList;
	
	public TestingView getView(){
		List<String> viewList = Stream.of(TestingView.values())
			.map(Enum::name)
			.collect(Collectors.toList());

		if(viewList.contains(this.view)) return TestingView.valueOf(this.view);
		else return TestingView.DEFAULT;
	}
	
}
