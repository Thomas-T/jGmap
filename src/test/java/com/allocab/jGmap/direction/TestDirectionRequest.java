package com.allocab.jGmap.direction;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.allocab.jGmap.common.Avoid;
import com.allocab.jGmap.common.Language;
import com.allocab.jGmap.common.Location;
import com.allocab.jGmap.common.TLD;
import com.allocab.jGmap.common.TravelMode;
import com.allocab.jGmap.common.Unit;
import com.allocab.jGmap.request.Fetcher;
import com.allocab.jGmap.request.direction.DirectionRequest;
import com.allocab.jGmap.response.AbstractResponse.Status;
import com.allocab.jGmap.response.direction.DirectionResponse;
import com.allocab.jGmap.response.direction.DirectionResponse.Comparison;
import com.allocab.jGmap.response.direction.Point;

public class TestDirectionRequest {
	
	@Test
	public void testSetOrigin() {
		Location origin = Location.gen().address("102 avenue maréchal de saxe, lyon");
		DirectionRequest req = new DirectionRequest();
		req.setOrigin(origin);
		assertSame(origin, req.origin);
	}
	
	@Test
	public void testSetDestination() {
		Location destination = Location.gen().address("102 avenue maréchal de saxe, lyon");
		DirectionRequest req = new DirectionRequest();
		req.setDestination(destination);
		assertSame(destination, req.destination);
	}
	
	@Test
	public void testSetMode() {
		DirectionRequest req = new DirectionRequest();
		req.setMode(TravelMode.BICYCLING);
		assertEquals(TravelMode.BICYCLING, req.mode);
	}
	
	@Test
	public void testSetAlternatives() {
		DirectionRequest req = new DirectionRequest();
		req.setAlternatives(true);;
		assertEquals(true, req.alternatives);
	}
	
	@Test
	public void testSetLanguage() {
		DirectionRequest req = new DirectionRequest();
		req.setLanguage(Language.fr);
		assertEquals(Language.fr, req.language);
	}
	
	@Test
	public void testSetUnits() {
		DirectionRequest req = new DirectionRequest();
		req.setUnits(Unit.imperial);
		assertEquals(Unit.imperial, req.units);
	}
	
	@Test
	public void testSetRegion() {
		DirectionRequest req = new DirectionRequest();
		req.setRegion(TLD.fr);
		assertEquals(TLD.fr, req.getRegion());
	}
	
	@Test
	public void testSetDepartureTime() {
		Date date = new Date();
		DirectionRequest req = new DirectionRequest();
		req.setDeparture_time(date);
		assertEquals(date.getTime(), req.departure_time.getTime());
	}
	
	@Test
	public void testSetArrivalTime() {
		Date date = new Date();
		DirectionRequest req = new DirectionRequest();
		req.setArrival_time(date);
		assertEquals(date.getTime(), req.arrival_time.getTime());
	}
	
	@Test
	public void testSetAvoid() {
		DirectionRequest req = new DirectionRequest();
		req.setAvoid(Avoid.highways);
		assertEquals(Avoid.highways, req.avoid);
	}
	
	@Test
	public void testOriginPoint() {
		Point point = new Point(5d,9d);		
		
		DirectionRequest req = new DirectionRequest();
		assertSame(req, req.origin(point));
		assertNotNull(req.origin);
		assertNotNull(req.origin.getPoint());
		assertSame(point, req.origin.getPoint());
	}
	
	@Test
	public void testOriginDouble() {		
		DirectionRequest req = new DirectionRequest();
		assertSame(req, req.origin(12f, 34f));
		assertNotNull(req.origin);
		assertNotNull(req.origin.getPoint());
		assertEquals(12f, req.origin.getPoint().getLat(), 0.0f);
		assertEquals(34f, req.origin.getPoint().getLng(), 0.0f);
	}
	
	@Test
	public void testDestinationPoint() {
		Point point = new Point(5d,9d);		
		
		DirectionRequest req = new DirectionRequest();
		assertSame(req, req.destination(point));
		assertNotNull(req.destination);
		assertNotNull(req.destination.getPoint());
		assertSame(point, req.destination.getPoint());
	}
	
	@Test
	public void testDestinationDouble() {		
		DirectionRequest req = new DirectionRequest();
		assertSame(req, req.destination(12f, 34f));
		assertNotNull(req.destination);
		assertNotNull(req.destination.getPoint());
		assertEquals(12f, req.destination.getPoint().getLat(), 0.0f);
		assertEquals(34f, req.destination.getPoint().getLng(), 0.0f);
	}
	
	@Test
	public void testAlternatives() {		
		DirectionRequest req = new DirectionRequest();
		assertSame(req, req.alternatives(true));
		assertTrue(req.alternatives);
	}
	

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
