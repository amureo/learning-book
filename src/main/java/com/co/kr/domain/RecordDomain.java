package com.co.kr.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RecordDomain {
	private Integer id;
	private Integer workbook;
	private LocalDateTime createAt;
}
