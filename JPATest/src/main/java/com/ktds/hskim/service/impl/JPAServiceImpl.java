package com.ktds.hskim.service.impl;

import com.ktds.hskim.biz.JPABiz;
import com.ktds.hskim.domain.JPA;
import com.ktds.hskim.service.JPAService;

public class JPAServiceImpl implements JPAService {
	
	private JPABiz jpaBiz;
	public void setJpaBiz(JPABiz jpaBiz) {
		this.jpaBiz = jpaBiz;
	}

	@Override
	public void insertData() {
		JPA jpa = jpaBiz.insertData();
		
		jpa.setSubject("수정");
		jpaBiz.updateData(jpa);
	}

	
}
