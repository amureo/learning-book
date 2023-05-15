package com.co.kr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.kr.domain.CategoryDomain;
import com.co.kr.domain.ProblemDomain;
import com.co.kr.domain.RecordDomain;
import com.co.kr.domain.SearchDomain;
import com.co.kr.domain.WorkbookDomain;
import com.co.kr.mapper.WorkbookMapper;
import com.co.kr.sort.ProblemSort;
import com.co.kr.sort.ProblemSortStd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class WorkbookServiceImpl implements WorkbookService{
	
	@Autowired
	WorkbookMapper workbookMapper;
	
	@Override
	public void insertWorkbook(WorkbookDomain workbookDomain, HttpServletRequest request, HttpServletRequest httpReq) {
		workbookMapper.insertWorkbook(workbookDomain);
	}

	@Override
	public List<WorkbookDomain> selectAllWorkbook(Map map){
		return workbookMapper.selectAllWorkbook(map);
	}
	
	@Override
	public WorkbookDomain selectOneWorkbook(Map map) {
		return workbookMapper.selectOneWorkbook(map);
	}

	@Override
	public void updateWorkbook(WorkbookDomain workbookDomain) {
		workbookMapper.updateWorkbook(workbookDomain);
	}

	@Override
	public void deleteWorkbook(Map map) {
		workbookMapper.deleteWorkbook(map);
		workbookMapper.updateWorkbookRanking(map);
	}
	

	@Override
	public List<WorkbookDomain> selectAllWorkbookSort(Map map){
		return workbookMapper.selectAllWorkbookSort(map);
	}
	
	
	
	
	/*
	 * 
	 * problem
	 * 
	 */
	@Override
	public void insertProblem(ProblemDomain problemDomain) {
		workbookMapper.insertProblem(problemDomain);
		

		Map map=new HashMap<String,Object>();
		int workbookId=problemDomain.getWorkbook();
		map.put("workbookId", workbookId);
		
		workbookMapper.updateRanking(map);
	}
	@Override
	public void insertProblemBlank(ProblemDomain problemDomain) {
		workbookMapper.insertProblem(problemDomain);
		
		Map map=new HashMap<String,Object>();
		int workbookId=problemDomain.getWorkbook();
		map.put("workbookId", workbookId);
		workbookMapper.updateRanking(map);
	}
	@Override
	public List<ProblemDomain> selectAllProblem(Map map){
		return workbookMapper.selectAllProblem(map);
	}
	@Override
	public ProblemDomain selectOneProblem(Map map){
		return workbookMapper.selectOneProblem(map);
	}
	@Override
	public void updateProblem(ProblemDomain problemDomain) {
		workbookMapper.updateProblem(problemDomain);
	}
	@Override
	public void deleteProblem(Map map) {
		int workbookId=this.selectOneProblem(map).getWorkbook();
		map.put("workbookId", workbookId);
		
		workbookMapper.deleteProblem(map);
		workbookMapper.updateRanking(map);
	}
	

	@Override
	public ProblemDomain selectPrevProblem(Map map) {
		ProblemDomain problemDomain =this.selectOneProblem(map);
		int ranking=problemDomain.getRanking();
		int workbookId=problemDomain.getWorkbook();
		
		if(ranking==1) return null;
		map.put("ranking", ranking);
		return workbookMapper.selectPrevProblem(map);
	}
	@Override
	public ProblemDomain selectNextProblem(Map map) {
		ProblemDomain problemDomain =this.selectOneProblem(map);
		int ranking=problemDomain.getRanking();
		int workbookId=problemDomain.getWorkbook();
		map.put("workbookId", workbookId);
		
		int total=this.getTotalProblemByWorkbookId(map);
		if(ranking==total) return null;
		map.put("ranking", ranking);
		return workbookMapper.selectNextProblem(map);
	}
	@Override
	public int getTotalProblemByWorkbookId(Map map) {
		return workbookMapper.getTotalProblemByWorkbookId(map);
	}
	@Override
	public List<ProblemDomain> selectAllProblemSort(Map map){
		return workbookMapper.selectAllProblemSort(map);
	}
	
	

	/*
	 * 
	 * record
	 * 
	 */
	
	@Override
	public void insertRecord(RecordDomain recordDomain) {
		workbookMapper.insertRecord(recordDomain);
	}
	@Override
	public List<RecordDomain> selectAllRecord(Map map){
		return workbookMapper.selectAllRecord(map);
	}
	@Override
	public RecordDomain selectOneRecord(Map map){
		return workbookMapper.selectOneRecord(map);
	}
	@Override
	public void deleteRecord(Map map){
		workbookMapper.deleteRecord(map);
	}
	
	
	// category

	public void insertCategory(CategoryDomain categoryDomain) {
		workbookMapper.insertCategory(categoryDomain);
	}
	public List<CategoryDomain> selectAllCategory(){
		return workbookMapper.selectAllCategory();
	}
	public CategoryDomain selectOneCategory(Map map) {
		return workbookMapper.selectOneCategory(map);
	}
	public void updateCategory(CategoryDomain categoryDomain) {
		workbookMapper.updateCategory(categoryDomain);
	}
	public void deleteCategory(Map map) {
		workbookMapper.deleteCategory(map);
		
	}
	
	
	
	//search
	@Override
	public List<SearchDomain> searchProblem(Map map){
		return workbookMapper.searchProblem(map);
	}
	
	//home
	@Override
	public List<ProblemDomain> selectRandomProblem(Map map){
		return workbookMapper.selectRandomProblem(map);
		
	}
}
