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
		
		assertTrue(dr.getShortestRoute(Comparison.DISTANCE).getDistance().getValue() > 2200);		
	}
	
	@Test
	public void tesDirectionServiceCrypted() {
		
		AbstractRequest.setApiClientId(System.getenv("GMAP_CLIENT_ID"));
		AbstractRequest.setApiCryptoKey(System.getenv("GMAP_CRYPTO_KEY"));		
		
		DirectionResponse dr = DirectionService.gen().departure_time(new Date()).origin("19 rue de la viabert, lyon").destination("102 avenue saxe, lyon").call();
		assertNotNull(dr);
		assertEquals(Status.OK, dr.getStatus());
		
		assertTrue(dr.getShortestRoute(Comparison.DISTANCE).getDistance().getValue() > 2200);
		assertTrue(dr.getShortestRoute(Comparison.DURATION).getDistance().getValue() > 2200);
		assertTrue(dr.getShortestRoute(Comparison.DURATION_IN_TRAFFIC).getDistance().getValue() > 2200);
		
		AbstractRequest.setApiClientId(null);
		AbstractRequest.setApiCryptoKey(null);
	}
}
