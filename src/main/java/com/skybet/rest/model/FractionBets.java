package com.skybet.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Data
public class FractionBets {

	@JsonProperty("bet_id")
	private int betId;
	private String event;
	private String name;
	
	private int stake;
	@JsonProperty("transaction_id")
	private int transactionId;

	private Odds odds;

}