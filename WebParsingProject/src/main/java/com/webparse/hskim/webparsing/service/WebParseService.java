package com.webparse.hskim.webparsing.service;

import org.springframework.web.servlet.ModelAndView;

public interface WebParseService {

	public ModelAndView getWebParsingDatas();

	public ModelAndView getSearchingDatas(String keyword);

}
