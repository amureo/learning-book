package com.co.kr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.co.kr.domain.CategoryDomain;
import com.co.kr.domain.ProblemDomain;
import com.co.kr.domain.RecordDomain;
import com.co.kr.domain.SearchDomain;
import com.co.kr.domain.WorkbookDomain;

@Mapper
public interface WorkbookMapper {

	/*
	 * workbook
	 */
    //insert
    public void insertWorkbook(WorkbookDomain workbookDomain);
    //select all
    public List<WorkbookDomain> selectAllWorkbook(Map map);
    //select one by workbook id
    public WorkbookDomain selectOneWorkbook(Map map);
    // update
    public void updateWorkbook(WorkbookDomain workbookDomain);
    // delete
    public void deleteWorkbook(Map map);
    
    /*
     * 
     * problem
     * 
     */

    // problem select by workbook
    public List<ProblemDomain> selectAllProblem(Map map);
    // problem select by id
    public ProblemDomain selectOneProblem(Map map);
    // problem insert
    public void insertProblem(ProblemDomain problemDoamin);
    // problem update
    public void updateProblem(ProblemDomain problemDoamin);
    // problem delete
    public void deleteProblem(Map map);
    
    // problem option
    public ProblemDomain selectPrevProblem(Map map);
    public ProblemDomain selectNextProblem(Map map);
    public int getTotalProblemByWorkbookId(Map map); 
	public List<ProblemDomain> selectAllProblemSort(Map map);
    
    
    // record insert
	public void insertRecord(RecordDomain recordDomain);
	// record select
	public List<RecordDomain> selectAllRecord(Map map);
	public RecordDomain selectOneRecord(Map map);
	// record delete
	public void deleteRecord(Map map);
	
	
	// category create
	public void insertCategory(CategoryDomain categoryDomain);
	// category select all
	public List<CategoryDomain> selectAllCategory();
	// category select one by category id
	public CategoryDomain selectOneCategory(Map map);
	// category update
	public void updateCategory(CategoryDomain categoryDomain);
	// category delete by category id
	public void deleteCategory(Map map);
	
	
	//search
	public List<SearchDomain> searchProblem(Map map);
	
	//home
	public List<ProblemDomain> selectRandomProblem(Map map);
	
	//util
	public void updateRanking(Map map);
}