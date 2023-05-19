package com.co.kr.vo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.co.kr.sort.ProblemDetailView;
import com.co.kr.sort.ProblemView;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ProblemDetailVO {
	private String view;
	
	public ProblemDetailView getView(){
		List<String> viewList = Stream.of(ProblemDetailView.values())
			.map(Enum::name)
			.collect(Collectors.toList());
		
		if(viewList.contains(this.view)) return ProblemDetailView.valueOf(this.view);
		else return ProblemDetailView.DEFAULT;
	}
	
}
