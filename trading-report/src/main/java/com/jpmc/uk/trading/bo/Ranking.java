package com.jpmc.uk.trading.bo;

public class Ranking {

	private Double totalAmount;
	private Integer rank;
	private String entity;
	
	
	public Ranking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ranking(Integer rank, String entity,Double totalAmount) {
		super();
		this.totalAmount = totalAmount;
		this.rank = rank;
		this.entity = entity;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
}
