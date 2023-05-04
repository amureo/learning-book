package com.co.kr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.co.kr.domain.WorkbookDomain;

@Mapper
public interface WorkbookMapper {

    //insert
    public void insertWorkbook(WorkbookDomain workbookDomain);
    
    //select all
    public List<WorkbookDomain> selectAllWorkbook();
    
    
}