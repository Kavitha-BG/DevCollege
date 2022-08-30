package com.devcollege.entities;

import javax.validation.constraints.Positive;

public class StudentWallet {
	
	@Positive(message = "Amount must be positive numeric value.")
	private Float amount;

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
}
