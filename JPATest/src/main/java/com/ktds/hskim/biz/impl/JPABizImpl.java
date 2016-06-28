package com.ktds.hskim.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.ktds.hskim.biz.JPABiz;
import com.ktds.hskim.domain.JPA;
import com.ktds.hskim.domain.JPA2;
import com.ktds.hskim.repository.JPARepository;

public class JPABizImpl implements JPABiz {

	private JPARepository jpaRepository;
	public void setJpaRepository(JPARepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}


	@Override
	public JPA insertData() {
		
		JPA jpa = new JPA();
		jpa.setSubject("JPA Test");
		jpa.setDescription("JPA Description");
		
		JPA2 jpa2 = new JPA2();
		jpa2.setMemo("반갑");
		
		List<JPA2> jpa2List = new ArrayList<JPA2>();
		jpa2List.add(jpa2);
		jpa.setJpa2(jpa2List);
		
		jpaRepository.save(jpa);
		
		return jpa;
	}


	@Override
	public JPA updateData(JPA jpa) {
		jpaRepository.save(jpa);
		
		List<JPA> jpas = jpaRepository.findAll();
		
		return jpa;
	}

}




