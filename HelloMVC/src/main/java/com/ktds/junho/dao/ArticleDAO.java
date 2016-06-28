package com.ktds.junho.dao;

import java.util.List;

import com.ktds.junho.vo.EmployeesVO;

public interface ArticleDAO {
	
	public String getNowSystemDate();
	public List<EmployeesVO> getAllEmployeeInfo();

}
