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
public class OddsTest {


    @Autowired
    private JacksonTester<Odds> json;

    @Test
    public void testSerialization() throws IOException {
        Odds odds = new Odds();
        odds.setOdds(2.0);
        odds.setNumerator(1);
        odds.setDenominator(2);

        assertThat(this.json.write(odds))
                .extractingJsonPathValue("@.odds").isEqualTo(2.0);
    }
}