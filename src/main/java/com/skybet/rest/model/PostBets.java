package com.skybet.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostBets {

	@JsonProperty("bet_id")
	private int betId;
	private Odds odds;
	private int stake;

	

}
