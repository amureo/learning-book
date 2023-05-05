package com.co.kr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.co.kr.domain.CategoryDomain;
import com.co.kr.domain.ProblemDomain;
import com.co.kr.domain.RecordDomain;
import com.co.kr.domain.WorkbookDomain;
import com.co.kr.service.WorkbookService;
import com.co.kr.vo.TestingVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
@RequestMapping(value = "/")
public class UserController {
	@Autowired
	WorkbookService workbookService;

	// 진입점
	@GetMapping("/")
	public String index(){
		return "index.html";
	}
	

	// 진입점
	@GetMapping("workbook")
	public ModelAndView workbook(){
		ModelAndView mav=new ModelAndView();
		List<WorkbookDomain> items = workbookService.selectAllWorkbook();
		System.out.println("items ==> "+ items);
		mav.addObject("items", items);
		mav.setViewName("workbook.html"); 
		
		return mav;
	}
	
	@GetMapping("workbook/new")
	public String wbCreate(){
		return "workBook/wbCreate.html";
	}

	@PostMapping("workbook/new/upload")
	public String wbCreateUpload(WorkbookDomain workbookDomain, HttpServletRequest request, HttpServletRequest httpReq){
		System.out.println("insert items ==> "+ workbookDomain);
		workbookService.insertWorkbook(workbookDomain,request,httpReq);
		return "redirect:/workbook";
	}
	
	@GetMapping("workbook/{id}")
	public ModelAndView wbDetail(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		
		WorkbookDomain item = workbookService.selectOneWorkbook(map);
		System.out.println("detail("+id+") ==> "+ item);
		mav.addObject("item", item);
		
		List<ProblemDomain> items = workbookService.selectByWorkbook(map);
		System.out.println("detail("+id+") problem ==> "+ items);
		mav.addObject("items", items);
		
		mav.setViewName("workBook/wbDetail.html"); 
		
		return mav;
	}
	
	@GetMapping("workbook/update/{id}")
	public ModelAndView wbUpdate(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		
		WorkbookDomain item = workbookService.selectOneWorkbook(map);
		System.out.println("detail("+id+") ==> "+ item);
		mav.addObject("item", item);
		mav.setViewName("workBook/wbUpdate.html"); 
		
		return mav;
	}
	@PostMapping("workbook/update/upload")
	public String wbUpdateUpload(WorkbookDomain workbookDomain) {
		System.out.println("update ==> "+ workbookDomain);
		
		workbookService.updateWorkbook(workbookDomain);
		
		return "redirect:/workbook";
	}
	
	@GetMapping("workbook/delete/{id}")
	public String wbDelete(@PathVariable("id") String id) {
		System.out.println("delete ==> "+ id);
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		
		workbookService.deleteWorkbook(map);

		return "redirect:/workbook";
	}
	

	@GetMapping("workbook/pnew")
	public ModelAndView wbProblemNew(@RequestParam("workbook") String workbook) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("workbook", workbook);
		mav.setViewName("workBook/pCreate.html"); 
		
		return mav;
	}
	

	@PostMapping("workbook/pnew/upload")
	public String wbProblemNewUpload(ProblemDomain problemDomain) {
		
		System.out.println("insert items ==> "+ problemDomain);
		workbookService.insertProblem(problemDomain);
		
		return "redirect:/workbook/"+problemDomain.getWorkbook();
	}
	
	@GetMapping("problem/{id}")
	public ModelAndView pDetail(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		
		ProblemDomain item = workbookService.selectById(map);
		System.out.println("detail("+id+") ==> "+ item);
		mav.addObject("item", item);
		
		mav.setViewName("workBook/pDetail.html"); 
		
		return mav;
	}
	
	@GetMapping("problem/update/{id}")
	public ModelAndView pUpdate(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		
		ProblemDomain item = workbookService.selectById(map);
		System.out.println("detail("+id+") ==> "+ item);
		mav.addObject("item", item);
		mav.setViewName("workBook/pUpdate.html"); 
		
		return mav;
	}

	@PostMapping("problem/update/upload")
	public String pUpdateUpload(ProblemDomain problemDomain) {
		System.out.println("update ==> "+ problemDomain);
		
		workbookService.updateProblem(problemDomain);
		
		return "redirect:/problem/"+problemDomain.getId();
	}
	

	
	@GetMapping("problem/delete/{id}")
	public String pDelete(@PathVariable("id") String id) {
		System.out.println("delete ==> "+ id);
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		ProblemDomain problemDomain=workbookService.selectById(map);
		workbookService.deleteProblem(map);

		return "redirect:/workbook/"+problemDomain.getWorkbook();
	}

	@GetMapping("testing")
	public ModelAndView testing(@RequestParam("workbook") String workbook) {
		System.out.println("testing ==> "+ workbook);
		
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", workbook);
		
		List<ProblemDomain> items = workbookService.selectByWorkbook(map);
		
		mav.addObject("workbook", workbook);
		mav.addObject("total", items.size());
		mav.addObject("current", "0");
		mav.addObject("score", "0");
		mav.setViewName("testing.html"); 
	
		
		return mav;
	}
	
	@PostMapping("testing")
	public ModelAndView testingProgress(TestingVO testingVO) {
		int current=testingVO.getCurrent();
		int workbook=testingVO.getWorkbook();
		int score=testingVO.getScore();
		List<Integer> list=(List) testingVO.getList();
		
		System.out.println("testing problem ==> "+ current);
		
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", workbook);
		
		List<ProblemDomain> items = workbookService.selectByWorkbook(map);
		ProblemDomain item=null;
		
		
		// 정답 검사 -> 문제 로드 -> 끝내기
		//문제가 처음인가?	
		if(current>0) {
			
			// 오답 처리
			
			if(list==null) list=new ArrayList() {};
			
			// 오답 시 처리
			if(testingVO.getIsRight()==0) {
				int problem=testingVO.getProblem();
				list.add(problem);
			}else {
				// 정답 시 처리
				score++;
			}
			
			// 오답 넣기
			mav.addObject("list", list);
		}
		
		//어떤 문제를 가져오는가?
		if(current<=items.size()) {
			item=items.get(current-1);
		}else if(score<=items.size()){
			map.put("id", list.get(0));
			list.remove(0);
			item=workbookService.selectById(map);
		}

		

		//문제가 끝인가?
		if (score>items.size()){
			mav.addObject("end",0);
			RecordDomain recordDomain=new RecordDomain();
			recordDomain.setWorkbook(testingVO.getWorkbook());
			workbookService.insertRecord(recordDomain);
		}
		

		// 문제 넣기
		mav.addObject("item", item);

		//점수 초기화
		mav.addObject("score",score);
		mav.addObject("workbook", workbook);
		mav.addObject("total", items.size());
		mav.addObject("current", current);
		mav.setViewName("testing.html"); 
		
		return mav;
	}
	
	@GetMapping("record")
	public ModelAndView record() {
		ModelAndView mav=new ModelAndView();
		
		List<RecordDomain> items = workbookService.selectRecord();

		mav.addObject("items",items);
		mav.setViewName("record.html"); 
		return mav;
	}
	

	@GetMapping("record/delete/{id}")
	public String rDelete(@PathVariable("id") String id) {
		System.out.println("delete record ==> "+ id);
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		workbookService.deleteRecord(map);

		return "redirect:/record";
	}

	
	
	/*
	 * 
	 * category
	 * 
	 */

	@GetMapping("category")
	public ModelAndView category() {
		ModelAndView mav=new ModelAndView();
		
		List<CategoryDomain> categoryList = workbookService.selectAllCategory();

		mav.addObject("categoryList",categoryList);
		mav.setViewName("category.html"); 
		return mav;
	}
	
	@GetMapping("category/new")
	public String categoryCreate() {
		return "workbook/cCreate.html";
	}

	@PostMapping("category/new/upload")
	public String categoryCreate(CategoryDomain categoryDomain) {
		workbookService.insertCategory(categoryDomain);
		return "redirect:/category";
	}
	@GetMapping("category/delete/{id}")
	public String categoryCreateUpload(@PathVariable("id") String id) {
		Map map = new HashMap<String, String>();
		map.put("id", id);
		workbookService.deleteCategory(map);
		return "redirect:/category";
	}
	
	@GetMapping("category/{id}")
	public ModelAndView categoryDetail(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		Map map = new HashMap<String, String>();
		map.put("id", id);
		CategoryDomain category = workbookService.selectOneCategory(map);
		mav.addObject("item",category);
		mav.setViewName("workbook/cDetail.html"); 
		return mav;
	}

	@GetMapping("category/update/{id}")
	public ModelAndView categoryUpdate(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		Map map = new HashMap<String, String>();
		map.put("id", id);
		CategoryDomain category = workbookService.selectOneCategory(map);
		mav.addObject("item",category);
		mav.setViewName("workbook/cUpdate.html"); 
		return mav;
	}
	@PostMapping("category/update/upload")
	public String categoryUpdateUpload(CategoryDomain categoryDomain) {
		workbookService.updateCategory(categoryDomain);
		return "redirect:/category";
	}
	
}