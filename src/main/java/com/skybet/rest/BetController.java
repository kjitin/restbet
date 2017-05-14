package com.skybet.rest;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.math.Fraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.skybet.rest.exception.BetException;
import com.skybet.rest.model.DecimalBets;
import com.skybet.rest.model.FractionBets;
import com.skybet.rest.model.InputBets;
import com.skybet.rest.model.Odds;
import com.skybet.rest.model.PostBets;


@RestController
public class BetController {

	@Autowired
	Environment env;


	/**
	 * Used to get all Available bets
	 * @return List<DecimalBets>
	 */
	@RequestMapping(value="/v1/available",method=RequestMethod.GET)
	public List<DecimalBets> getAvailableBets(){
		RestTemplate restTemplate = new RestTemplate();
		FractionBets[] bets =restTemplate.getForObject(env.getProperty("location.available"), FractionBets[].class);
		
		List<FractionBets> fractionList = Arrays.asList(bets);		
		
		return fractionList.stream().map(convertBets).collect(Collectors.<DecimalBets> toList());
	}


	/**
	 * Used to POST new Bets
	 * @param inputBets
	 * @return Created DecimalBets
	 */
	@RequestMapping(value="/v1/bets",method=RequestMethod.POST,consumes="application/json")
	public DecimalBets postBets(@RequestBody @Valid InputBets inputBets){
		RestTemplate restTemplate = new RestTemplate();

		
		PostBets postBets =convertInputToPost.apply(inputBets);


		ResponseEntity<FractionBets> responseBets;

		try{
		responseBets =restTemplate.exchange(env.getProperty("location.bets"),HttpMethod.POST, getPostBetsHttpEntity(postBets), FractionBets.class);
		}
		catch(RestClientException exception){
			throw new BetException(exception.getMessage());
		}
		return convertBets.apply(responseBets.getBody());
		
	}

	/**
	 * Used to create PostBets HttpEntity
	 * @param postBets
	 * @return
	 */

	private HttpEntity<PostBets> getPostBetsHttpEntity(PostBets postBets) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(postBets, headers);
	}


	/**
	 * Lambda function to convert Input to Post
	 */
	
	Function<InputBets,PostBets> convertInputToPost = inputBets -> {
        PostBets postBets = new PostBets();
        postBets.setBetId(inputBets.getBetId());
        Odds odds = new Odds();
        Fraction decimalFraction =Fraction.getFraction(inputBets.getOdds()-1.0);
        odds.setNumerator(decimalFraction.getNumerator());
        odds.setDenominator(decimalFraction.getDenominator());
        postBets.setOdds(odds);
        postBets.setStake(inputBets.getStake());
        return postBets;
    };

	/**
	 * Lambda function to convert FractionBets to DecimaBets
	 */

	Function<FractionBets,DecimalBets> convertBets = fractionBets -> {
        DecimalBets decBets = new DecimalBets();
        decBets.setBetId(fractionBets.getBetId());
        decBets.setEvent(fractionBets.getEvent());
        decBets.setName(fractionBets.getName());
        decBets.setStake(fractionBets.getStake());
        decBets.setTransactionId(fractionBets.getTransactionId());

        Odds odds = fractionBets.getOdds();
        decBets.setOdds(getDecimalValue(odds.getNumerator(),odds.getDenominator()));
        return decBets;
	};

	/**
	 * Used to convert fractional values to Decimal format
	 * @param numerator of fraction
	 * @param denominator of fraction
	 * @return Decimal Value
	 */
	public Double getDecimalValue(int numerator, int denominator) {
		DecimalFormat twoDForm = new DecimalFormat("##.##");
		Double result = Fraction.getFraction(numerator, denominator).doubleValue()+1.0;
		return Double.valueOf(twoDForm.format(result));
	}
	
		

}
