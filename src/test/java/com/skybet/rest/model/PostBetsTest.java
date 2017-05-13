package com.skybet.rest.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@JsonTest
public class PostBetsTest {

    @Autowired
    private JacksonTester<PostBets> json;

    @Test
    public void testSerialization() throws IOException {
        PostBets postBets = new PostBets();
        postBets.setBetId(1);
        postBets.setOdds(new Odds());
        postBets.setStake(1);

        assertThat(this.json.write(postBets))
                .extractingJsonPathValue("@.bet_id").isEqualTo(1);
    }
}