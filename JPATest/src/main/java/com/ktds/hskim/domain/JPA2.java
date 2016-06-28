package com.ktds.hskim.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JPA2")
public class JPA2 {
	
	@Id
	@Column(insertable=false, updatable=false)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="JPA_ID", updatable=false, insertable=false)
	private JPA jpa;
	
	private String memo;
	
	
	public JPA getJpa() {
		return jpa;
	}
	public void setJpa(JPA jpa) {
		this.jpa = jpa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
