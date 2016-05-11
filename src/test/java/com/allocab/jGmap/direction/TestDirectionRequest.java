package com.allocab.jGmap.direction;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.jGmap.request.Fetcher;
import com.allocab.jGmap.request.direction.DirectionRequest;
import com.allocab.jGmap.response.AbstractResponse.Status;
import com.allocab.jGmap.response.direction.DirectionResponse;
import com.allocab.jGmap.response.direction.DirectionResponse.Comparison;

public class TestDirectionRequest {

	@Test
	public void testLyonDirection() {
		
		DirectionResponse response = Fetcher.gen(DirectionRequest.gen().origin("19 rue viabert, lyon 69006").destination("102 avenue saxe, lyon 69003")).call();
		assertNotNull(response);
		assertEquals(Status.OK, response.getStatus());
		
		assertTrue(response.getShortestRoute(Comparison.DISTANCE).getDistance().getValue() > 2200);
		
		response = Fetcher.gen(DirectionRequest.gen().origin("19 rue viabert, lyon 69006").destination("102 avenue saxe, lyon 69003")).call();
		assertNotNull(response);
		assertEquals(Status.OK, response.getStatus());
		
		assertTrue(response.getShortestRoute(Comparison.DISTANCE).getDistance().getValue() > 2200);
		
	}
	
}
