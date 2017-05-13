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
public class DecimalBetsTest {


    @Autowired
    private JacksonTester<DecimalBets> json;

    @Test
    public void testSerialization() throws IOException {
        DecimalBets decimalBets = new DecimalBets();
        decimalBets.setBetId(1);
        decimalBets.setEvent("test");
        decimalBets.setName("testName");

        assertThat(this.json.write(decimalBets))
                .extractingJsonPathStringValue("@.event")
                .isEqualTo("test");
    }
}