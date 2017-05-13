package com.skybet.rest.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class InputBets {
		
	@Min(1)
	@JsonProperty("bet_id")
	private int betId;
	@NotNull
	private Double odds;
	@NotNull
	private int stake;
	

}
