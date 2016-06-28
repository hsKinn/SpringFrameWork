package com.ktds.hskim.article.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticleController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
}
