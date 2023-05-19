package com.co.kr.vo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.co.kr.sort.ProblemSort;
import com.co.kr.sort.ProblemSortStd;
import com.co.kr.sort.ProblemView;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ProblemSortVO{
	private String sort;
	private String sortStd;
	private String view;
	
	public ProblemSort getSort(){
		List<String> sortList = Stream.of(ProblemSort.values())
			.map(Enum::name)
			.collect(Collectors.toList());

		if(sortList.contains(this.sort)) return ProblemSort.valueOf(this.sort);
		else return ProblemSort.DEFAULT;
	}

	public ProblemSortStd getSortStd(){
		List<String> sortStdList = Stream.of(ProblemSortStd.values())
			.map(Enum::name)
			.collect(Collectors.toList());

		if(sortStdList.contains(this.sortStd)) return ProblemSortStd.valueOf(this.sortStd);
		else return ProblemSortStd.DEFAULT;
	}

	public ProblemView getView(){
		List<String> sortStdList = Stream.of(ProblemView.values())
			.map(Enum::name)
			.collect(Collectors.toList());

		if(sortStdList.contains(this.view)) return ProblemView.valueOf(this.view);
		else return ProblemView.DEFAULT;
	}
	
}
