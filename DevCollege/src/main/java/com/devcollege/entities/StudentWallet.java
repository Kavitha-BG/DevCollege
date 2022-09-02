package com.devcollege.entities;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class StudentWallet {

	@Positive(message = "Amount must be positive numeric value.")
//	@Pattern(regexp = "^[0-9]*$", message = "Amount should be in numbers")
	private Float amount;

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	

}
