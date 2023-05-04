package com.co.kr.controller;

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

import com.co.kr.domain.ProblemDomain;
import com.co.kr.domain.WorkbookDomain;
import com.co.kr.service.WorkbookService;

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
}