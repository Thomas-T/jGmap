package com.allocab.JGMap.direction;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.allocab.JGMap.request.AbstractRequest;
import com.allocab.JGMap.response.AbstractResponse.Status;
import com.allocab.JGMap.response.direction.DirectionResponse;
import com.allocab.JGMap.response.direction.DirectionResponse.Comparison;
import com.allocab.JGMap.service.DirectionService;

public class TestDirectionService {

	@Test
	public void tesDirectionService() {
		
		DirectionResponse dr = DirectionService.gen().origin("19 rue de la viabert, lyon").destination("102 avenue saxe, lyon").call();
		assertNotNull(dr);
		assertEquals(Status.OK, dr.getStatus());
		
		assertEquals(2250, dr.getShortestRoute(Comparison.DISTANCE).getDistance().getValue());		
	}
	
	@Test
	public void tesDirectionServiceCrypted() {
		
		String apiKey = null;
		String client = null;
		
		AbstractRequest.setApiClientId(client);
		AbstractRequest.setApiCryptoKey(apiKey);
		
		
		DirectionResponse dr = DirectionService.gen().departure_time(new Date()).origin("19 rue de la viabert, lyon").destination("102 avenue saxe, lyon").call();
		assertNotNull(dr);
		assertEquals(Status.OK, dr.getStatus());
		
		assertEquals(2250, dr.getShortestRoute(Comparison.DISTANCE).getDistance().getValue());
		
		AbstractRequest.setApiClientId(null);
		AbstractRequest.setApiCryptoKey(null);
	}
}
