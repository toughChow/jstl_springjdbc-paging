package com.qingshixun.project.paging.model;

import java.io.Serializable;

public class TransactionModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private double ammount;// 销售金额
	private String region;// 地区
	private Integer month;// 月份
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
}
