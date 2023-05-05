package com.co.kr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.co.kr.domain.CategoryDomain;
import com.co.kr.domain.ProblemDomain;
import com.co.kr.domain.RecordDomain;
import com.co.kr.domain.WorkbookDomain;

@Mapper
public interface WorkbookMapper {

    //insert
    public void insertWorkbook(WorkbookDomain workbookDomain);
    
    //select all
    public List<WorkbookDomain> selectAllWorkbook();
    
    //select one
    public WorkbookDomain selectOneWorkbook(Map map);
    
    // update
    public void updateWorkbook(WorkbookDomain workbookDomain);

    //delete
    public void deleteWorkbook(Map map);
    

    // problem select by workbook
    public List<ProblemDomain> selectByWorkbook(Map map);
    // problem select by id
    public ProblemDomain selectById(Map map);
    // problem insert
    public void insertProblem(ProblemDomain problemDoamin);
    // problem update
    public void updateProblem(ProblemDomain problemDoamin);
    // problem delete
    public void deleteProblem(Map map);
    
    // record insert
	public void insertRecord(RecordDomain recordDomain);
	// record select
	public List<RecordDomain> selectRecord();
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
	
}