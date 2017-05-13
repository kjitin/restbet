package com.skybet.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({"bet_id","event","name","odds","stake","transaction_id"})
@Data
public class DecimalBets {

	@JsonProperty("bet_id")
	private int betId;
	private String event;
	private String name;
	private int stake;
	@JsonProperty("transaction_id")
	private int transactionId;

	private Double odds;
		

	
}
