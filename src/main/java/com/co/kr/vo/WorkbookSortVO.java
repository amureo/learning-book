package com.co.kr.vo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.co.kr.sort.WorkbookSort;
import com.co.kr.sort.WorkbookSortStd;
import com.co.kr.sort.WorkbookView;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class WorkbookSortVO{
	private String sort;
	private String sortStd;
	private String view;
	
	public WorkbookSort getSort(){
		List<String> sortList = Stream.of(WorkbookSort.values())
			.map(Enum::name)
			.collect(Collectors.toList());

		if(sortList.contains(this.sort)) return WorkbookSort.valueOf(this.sort);
		else return WorkbookSort.DEFAULT;
	}

	public WorkbookSortStd getSortStd(){
		List<String> sortStdList = Stream.of(WorkbookSortStd.values())
			.map(Enum::name)
			.collect(Collectors.toList());

		if(sortStdList.contains(this.sortStd)) return WorkbookSortStd.valueOf(this.sortStd);
		else return WorkbookSortStd.DEFAULT;
	}
	public WorkbookView getView() {
		List<String> sortStdList = Stream.of(WorkbookView.values())
				.map(Enum::name)
				.collect(Collectors.toList());

			if(sortStdList.contains(this.view)) return WorkbookView.valueOf(this.view);
			else return WorkbookView.DEFAULT;
	}
	
}
