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
public class FractionBetsTest {


    @Autowired
    private JacksonTester<FractionBets> json;

    @Test
    public void testSerialization() throws IOException {
        FractionBets fractionBets = new FractionBets();
        fractionBets.setName("test");
        fractionBets.setEvent("test");

        assertThat(this.json.write(fractionBets))
                .extractingJsonPathStringValue("@.event")
                .isEqualTo("test");
    }
}