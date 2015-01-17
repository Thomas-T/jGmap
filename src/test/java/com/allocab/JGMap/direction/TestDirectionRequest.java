package com.allocab.JGMap.direction;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.JGMap.request.Fetcher;
import com.allocab.JGMap.request.direction.DirectionRequest;
import com.allocab.JGMap.response.AbstractResponse.Status;
import com.allocab.JGMap.response.direction.DirectionResponse;
import com.allocab.JGMap.response.direction.DirectionResponse.Comparison;
import com.allocab.JGMap.response.direction.Point;

public class TestDirectionRequest {

	@Test
	public void testLyonDirection() {
		
		DirectionResponse response = Fetcher.gen(DirectionRequest.gen().origin("19 rue viabert, lyon 69006").destination("102 avenue saxe, lyon 69003")).call();
		assertNotNull(response);
		assertEquals(Status.OK, response.getStatus());
		
		assertEquals(2250, response.getShortestRoute(Comparison.DISTANCE).getDistance().getValue());
		
		response = Fetcher.gen(DirectionRequest.gen().origin("19 rue viabert, lyon 69006").destination("102 avenue saxe, lyon 69003")).call();
		assertNotNull(response);
		assertEquals(Status.OK, response.getStatus());
		
		assertEquals(2250, response.getShortestRoute(Comparison.DISTANCE).getDistance().getValue());
		
	}
	
}
