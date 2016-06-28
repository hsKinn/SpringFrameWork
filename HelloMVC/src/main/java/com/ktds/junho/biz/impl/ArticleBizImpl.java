package com.ktds.junho.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.junho.biz.ArticleBiz;
import com.ktds.junho.dao.ArticleDAO;
import com.ktds.junho.vo.EmployeesVO;

public class ArticleBizImpl implements ArticleBiz {

	private Logger logger = LoggerFactory.getLogger(ArticleBizImpl.class);
	private ArticleDAO articleDAO;
	
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public void insertNewArticle() {
		// TODO Auto-generated method stub
		logger.debug("insertNewArticle을 호출했습니다~");
		String nowDate = articleDAO.getNowSystemDate();
		logger.info("현재 시간은 {} 입니다.", nowDate);
	}

	// select 쿼리 결과 갖고 오기
	@Override
	public List<EmployeesVO> getAllEmployeeInfo() {
		// TODO Auto-generated method stub
		return articleDAO.getAllEmployeeInfo();
	}

}
