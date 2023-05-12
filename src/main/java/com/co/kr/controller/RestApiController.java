package com.co.kr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.co.kr.domain.CategoryDomain;
import com.co.kr.domain.ProblemDomain;
import com.co.kr.domain.RecordDomain;
import com.co.kr.domain.WorkbookDomain;
import com.co.kr.service.WorkbookService;
import com.co.kr.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j 
public class RestApiController {
	@Autowired
	WorkbookService workbookService;
	
	/*
	 * 
	 * workbook
	 * 
	 */
	
	@RequestMapping(value="/api/workbook",method=RequestMethod.POST)
	public void workbookCreate(@RequestBody WorkbookDomain workbookDomain, HttpServletRequest request, HttpServletRequest httpReq) {
		Map map=CommonUtils.getMember(request);
		workbookDomain.setOwner((Integer)map.get("owner"));
		workbookService.insertWorkbook(workbookDomain,request,httpReq);
	}
	@RequestMapping(value="/api/workbook",method=RequestMethod.GET)
	public List<WorkbookDomain> workbookList(HttpServletRequest request) {
		Map map=CommonUtils.getMember(request);
		return workbookService.selectAllWorkbook(map);
	}
	@RequestMapping(value="/api/workbook/{id}",method=RequestMethod.GET)
	public WorkbookDomain workbookOne(@PathVariable("id") String id, HttpServletRequest request) {
		Map map=CommonUtils.getMember(request);
		map.put("id", id);
		return workbookService.selectOneWorkbook(map);
	}
	@RequestMapping(value="/api/workbook/{id}",method=RequestMethod.PUT)
	public void workbookModify(@PathVariable("id") String id, @RequestBody WorkbookDomain workbookDomain, HttpServletRequest request) {
		Map map=CommonUtils.getMember(request);
		workbookDomain.setOwner((Integer)map.get("owner"));
		workbookService.updateWorkbook(workbookDomain);
	}
	@RequestMapping(value="/api/workbook/{id}",method=RequestMethod.DELETE)
	public void workbookRemove(@PathVariable("id") String id, HttpServletRequest request) {
		Map map=CommonUtils.getMember(request);
		map.put("id", id);
		workbookService.deleteWorkbook(map);
	}
	
	

	/*
	 * 
	 * problem
	 * 
	 */
	
	@RequestMapping(value="/api/problem",method=RequestMethod.POST)
	public ProblemDomain problemCreate(@RequestBody ProblemDomain problemDomain) {
		Map map = new HashMap<String, String>();
		workbookService.insertProblemBlank(problemDomain);
		map.put("id", problemDomain.getId());
		return workbookService.selectOneProblem(map);
	}
	@RequestMapping(value="/api/problem/w/{id}",method=RequestMethod.GET)
	public List<ProblemDomain> problemList(@PathVariable("id") String id) {
		Map map = new HashMap<String, String>();
		map.put("id", id);
		return workbookService.selectAllProblem(map);
	}
	@RequestMapping(value="/api/problem/p/{id}",method=RequestMethod.GET)
	public ProblemDomain problemOne(@PathVariable("id") String id, HttpServletRequest request) {
		Map map = new HashMap<String, String>();
		map.put("id", id);
		return workbookService.selectOneProblem(map);
	}
	@RequestMapping(value="/api/problem/{id}",method=RequestMethod.PUT)
	public void problemModify(@PathVariable("id") String id, @RequestBody ProblemDomain problemDomain) {
		Map map = new HashMap<String, String>();
		map.put("id", id);
		workbookService.updateProblem(problemDomain);
	}
	@RequestMapping(value="/api/problem/{id}",method=RequestMethod.DELETE)
	public void problemRemove(@PathVariable("id") String id) {
		Map map = new HashMap<String, String>();
		map.put("id", id);
		workbookService.deleteProblem(map);
	}
	
	
	
	/*
	 * 
	 * category
	 * 
	 */
	
	@RequestMapping(value="/api/category",method=RequestMethod.POST)
	public void categoryCreate(@RequestBody CategoryDomain categoryDomain) {
		workbookService.insertCategory(categoryDomain);
	}
	@RequestMapping(value="/api/category",method=RequestMethod.GET)
	public List<CategoryDomain> categoryList() {
		return workbookService.selectAllCategory();
	}
	@RequestMapping(value="/api/category/{id}",method=RequestMethod.GET)
	public CategoryDomain categoryOne(@PathVariable("id") String id) {
		Map map = new HashMap<String, String>();
		map.put("id", id);
		return workbookService.selectOneCategory(map);
	}
	@RequestMapping(value="/api/category/{id}",method=RequestMethod.PUT)
	public void categoryModify(@PathVariable("id") String id, @RequestBody CategoryDomain categoryDomain) {
		workbookService.updateCategory(categoryDomain);
	}
	@RequestMapping(value="/api/category/{id}",method=RequestMethod.DELETE)
	public void categoryRemove(@PathVariable("id") String id) {
		Map map = new HashMap<String, String>();
		map.put("id", id);
		workbookService.deleteCategory(map);
	}
	

	/*
	 * 
	 * record
	 * 
	 */
	
	@RequestMapping(value="/api/record",method=RequestMethod.POST)
	public void recordCreate(@RequestBody RecordDomain recordDomain) {
		workbookService.insertRecord(recordDomain);
	}
	@RequestMapping(value="/api/record",method=RequestMethod.GET)
	public List<RecordDomain> recordList(HttpServletRequest request) {
		Map map=CommonUtils.getMember(request);
		return workbookService.selectAllRecord(map);
	}
	@RequestMapping(value="/api/record/{id}",method=RequestMethod.GET)
	public RecordDomain recordOne(@PathVariable("id") String id, HttpServletRequest request) {
		Map map=CommonUtils.getMember(request);
		map.put("id", id);
		return workbookService.selectOneRecord(map);
	}
	@RequestMapping(value="/api/record/{id}",method=RequestMethod.DELETE)
	public void recordRemove(@PathVariable("id") String id) {
		Map map = new HashMap<String, String>();
		map.put("id", id);
		workbookService.deleteRecord(map);
	}
}
