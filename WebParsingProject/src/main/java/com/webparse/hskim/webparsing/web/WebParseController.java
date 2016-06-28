package com.webparse.hskim.webparsing.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webparse.hskim.webparsing.service.WebParseService;

@Controller
public class WebParseController {

	private WebParseService parseService;
	public void setParseService(WebParseService parseService) {
		this.parseService = parseService;
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/getWebParsingDatas")
	public ModelAndView getWebParsingDatas() {
		
		return parseService.insertDatas();
	}
}
