package com.co.kr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.co.kr.domain.WorkbookDomain;

public interface WorkbookService {
	public void insertWorkbook(WorkbookDomain workbookDomain, HttpServletRequest request, HttpServletRequest httpReq);
	public List<WorkbookDomain> selectAllWorkbook();
}
