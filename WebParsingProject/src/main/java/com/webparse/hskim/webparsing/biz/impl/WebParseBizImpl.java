package com.webparse.hskim.webparsing.biz.impl;

import java.util.List;

import com.webparse.hskim.webparsing.biz.WebParseBiz;
import com.webparse.hskim.webparsing.dao.WebParseDAO;
import com.webparse.hskim.webparsing.vo.WebParseVO;

public class WebParseBizImpl implements WebParseBiz {
	
	private WebParseDAO parseDAO;

	@Override
	public void insertDatas(List<WebParseVO> datas) {
		
		for (WebParseVO webParseVO : datas) {
			parseDAO.insertDatas(webParseVO);
		}
		
	}

}
