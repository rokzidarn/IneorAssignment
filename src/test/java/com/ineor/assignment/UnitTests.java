package com.ineor.assignment;

import com.ineor.assignment.model.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnitTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/result/";
        URI uri = new URI(baseUrl);

        ResponseEntity<Result> result = this.restTemplate.getForEntity(uri, Result.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("China",result.getBody().getHighest_population().get(0).getName());
    }
}