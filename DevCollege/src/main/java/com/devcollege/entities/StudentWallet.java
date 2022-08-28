package com.devcollege.entities;

import javax.validation.constraints.Positive;

public class StudentWallet {
	
	@Positive
	private Float amount;

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
}
