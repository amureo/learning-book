package com.co.kr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}