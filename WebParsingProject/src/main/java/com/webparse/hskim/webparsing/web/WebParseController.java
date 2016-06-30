package com.webparse.hskim.webparsing.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webparse.hskim.webparsing.service.WebParseService;

@Controller
public class WebParseController {

	// WebParseService DI Setter
	private WebParseService webParseService;
	
	public void setWebParseService(WebParseService webParseService) {
		this.webParseService = webParseService;
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/getWebParsingDatas")
	public ModelAndView getWebParsingDatas() {
		return webParseService.getWebParsingDatas();
	}
	
	@RequestMapping("/searchingDatas")
	public ModelAndView getSearchingDatas(@RequestParam(required=false, defaultValue="") String keyword) {
		return webParseService.getSearchingDatas(keyword);
	}
	
	
}
