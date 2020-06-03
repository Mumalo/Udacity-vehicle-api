package com.udacity.pricing;

import com.jayway.jsonpath.JsonPath;
import com.udacity.pricing.domain.price.Price;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate template;

	HttpHeaders httpHeaders = new HttpHeaders();

	@Test
	public void contextLoads() {
	}

	private String serverRoot(){
		return String.format("http://localhost:%s", port);
	}


	@Test
	public void testGetAllPrices() throws Exception {
		HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
		ResponseEntity<String> response = template.exchange(
				createURLWithPort("prices"),  HttpMethod.GET, entity, String.class
		);
		List<Integer> totalPrices = JsonPath.read(response.getBody(), "$..prices.length()");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		assert totalPrices.get(0) == 4;

	}

	@Test
	public void testGetPrice(){
		HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
		ResponseEntity<String> response = template.exchange(
				createURLWithPort("prices/1"),  HttpMethod.GET, entity, String.class
		);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getCreatePrice(){
		Price price = new Price("XAF", BigDecimal.valueOf(9867.223), 3L);
		HttpEntity<Price> entity = new HttpEntity<>(price, httpHeaders);
		ResponseEntity<String> response = template.exchange(
				createURLWithPort("prices"),  HttpMethod.POST, entity, String.class
		);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void deletePrice(){
		HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
		ResponseEntity<String> response = template.exchange(
				createURLWithPort("prices/1"),  HttpMethod.DELETE, entity, String.class
		);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
