package com.co.kr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.co.kr.domain.CategoryDomain;
import com.co.kr.domain.ProblemDomain;
import com.co.kr.domain.RecordDomain;
import com.co.kr.domain.SearchDomain;
import com.co.kr.domain.WorkbookDomain;

public interface WorkbookService {
	
	// workbook
	public void insertWorkbook(WorkbookDomain workbookDomain, HttpServletRequest request, HttpServletRequest httpReq);
	public List<WorkbookDomain> selectAllWorkbook(Map map);
	public WorkbookDomain selectOneWorkbook(Map map);
	public void updateWorkbook(WorkbookDomain workbookDomain);
	public void deleteWorkbook(Map map);
	// workbook sort
	public List<WorkbookDomain> selectAllWorkbookSort(Map map);
	
	//problem
	public void insertProblem(ProblemDomain problemDomain);
	public void insertProblemBlank(ProblemDomain problemDomain);
	public List<ProblemDomain> selectAllProblem(Map map);
	public ProblemDomain selectOneProblem(Map map);
	public void updateProblem(ProblemDomain problemDomain);
	public void deleteProblem(Map map);
	
	// problem option
	public ProblemDomain selectPrevProblem(Map map);
	public ProblemDomain selectNextProblem(Map map);
	public int getTotalProblemByWorkbookId(Map map);
	// problem sort
	public List<ProblemDomain> selectAllProblemSort(Map map);
	
	
	//record
	public void insertRecord(RecordDomain recordDomain);
	public List<RecordDomain> selectAllRecord(Map map);
	public RecordDomain selectOneRecord(Map map);
	public void deleteRecord(Map map);
	
	
	//category
	public void insertCategory(CategoryDomain categoryDomain);
	public List<CategoryDomain> selectAllCategory(Map map);
	public CategoryDomain selectOneCategory(Map map);
	public void updateCategory(CategoryDomain categoryDomain);
	public void deleteCategory(Map map);
	

	//search
	public List<SearchDomain> searchProblem(Map map);
	
	
	//home
	public List<ProblemDomain> selectRandomProblem(Map map);
	
}
