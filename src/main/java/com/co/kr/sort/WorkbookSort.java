package com.co.kr.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
public enum WorkbookSort{
	ASC,
	DESC;

	public static WorkbookSort DEFAULT=WorkbookSort.ASC;
}