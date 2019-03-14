package com.jpmc.uk.trading.bo;

import java.time.LocalDate;

public class Instruction {

	private String entity;
	private String transcationType;
	private Double agreedFx;
	private String currenyType;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private Integer units;
	private Double pricePerUnit;
	private Double totalTransaction;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getTranscationType() {
		return transcationType;
	}

	public void setTranscationType(String transcationType) {
		this.transcationType = transcationType;
	}

	public Double getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(Double agreedFx) {
		this.agreedFx = agreedFx;
	}

	public String getCurrenyType() {
		return currenyType;
	}

	public void setCurrenyType(String currenyType) {
		this.currenyType = currenyType;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Double getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(Double totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

}
