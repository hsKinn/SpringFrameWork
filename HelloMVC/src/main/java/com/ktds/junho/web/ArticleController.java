package com.ktds.junho.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.junho.biz.ArticleBiz;
import com.ktds.junho.vo.EmployeesVO;

@Controller
public class ArticleController {
	private Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	private ArticleBiz articleBiz;
	
	public void setArticleBiz(ArticleBiz articleBiz) {
		this.articleBiz = articleBiz;
	}

	// view한테 어떤 데이터를 보내줄 것인지를 결정하는 것
	@RequestMapping("/list")
	public ModelAndView aritcleList() {
		
		articleBiz.insertNewArticle();
		
		logger.trace("트레이스");
		logger.debug("디버그");
		logger.info("인포");
		logger.warn("워닝");
		logger.error("에러");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("article/list");
		
		List<EmployeesVO> allEmployees = articleBiz.getAllEmployeeInfo();
		view.addObject("allEmployees", allEmployees);
		
		// request.setAttribute("key", "value");
		view.addObject("title", "제목");
		view.addObject("number", "1");
		view.addObject("author", "홍길동");
		
		return view;
	}
	
	// public ModelAndView detail(HttpServletRequest reuqest)
	
	@RequestMapping("/detail/{articleNumber}")
	public ModelAndView detail(@PathVariable int articleNumber) {
		ModelAndView view = new ModelAndView();
		view.setViewName("article/detail");
		view.addObject("articleNumber", articleNumber);
		return view;
	}
}
