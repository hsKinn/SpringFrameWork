package com.webparse.hskim.webparsing.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.webparse.hskim.webparsing.dao.WebParseDAO;
import com.webparse.hskim.webparsing.vo.WebParseVO;

public class WebParseDAOImpl extends SqlSessionDaoSupport implements WebParseDAO{

	@Override
	public void insertDatas(WebParseVO webParseVO) {
		getSqlSession().insert("WebParseDAO.insertDatas", webParseVO);
	}

}
