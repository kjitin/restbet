package com.skybet.rest.model;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class InputBetsTest {


    @Autowired
    private JacksonTester<InputBets> json;

    @Test
    public void testSerialization() throws IOException {
        InputBets inputBets = new InputBets();
        inputBets.setBetId(1);
        inputBets.setOdds(2.0);
        inputBets.setStake(1);

        assertThat(this.json.write(inputBets))
               .extractingJsonPathValue("@.bet_id").isEqualTo(1);
    }


}