package com.co.kr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.kr.domain.WorkbookDomain;
import com.co.kr.mapper.WorkbookMapper;

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
	public List<WorkbookDomain> selectAllWorkbook(){
		return workbookMapper.selectAllWorkbook();
	}

}
