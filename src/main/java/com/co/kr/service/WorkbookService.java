package com.co.kr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.co.kr.domain.ProblemDomain;
import com.co.kr.domain.RecordDomain;
import com.co.kr.domain.WorkbookDomain;

public interface WorkbookService {
	
	// workbook
	public void insertWorkbook(WorkbookDomain workbookDomain, HttpServletRequest request, HttpServletRequest httpReq);
	public List<WorkbookDomain> selectAllWorkbook();
	public WorkbookDomain selectOneWorkbook(Map map);
	public void updateWorkbook(WorkbookDomain workbookDomain);
	public void deleteWorkbook(Map map);
	
	//problem
	public List<ProblemDomain> selectByWorkbook(Map map);
	public ProblemDomain selectById(Map map);
	public void insertProblem(ProblemDomain problemDomain);
	public void updateProblem(ProblemDomain problemDomain);
	public void deleteProblem(Map map);
	
	//record
	public void insertRecord(RecordDomain recordDomain);
	public List<RecordDomain> selectRecord();
	public void deleteRecord(Map map);
}
