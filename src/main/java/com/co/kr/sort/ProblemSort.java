package com.co.kr.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProblemSort{
	ASC,
	DESC;

	public static ProblemSort DEFAULT=ProblemSort.ASC;
}
