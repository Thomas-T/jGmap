package com.allocab.jGmap.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.jGmap.request.DirectionRequest;
import com.allocab.jGmap.response.direction.Point;

public class DirectionServiceTest {
	
	public void testSetAndGetRequest() {
		DirectionService srv = new DirectionService();
		DirectionRequest req = new DirectionRequest();
		srv.setRequest(req);
		assertSame(req, srv.getRequest());
	}
	
	@Test
	public void testDestinationAsDouble() {		
		DirectionService srv = new DirectionService();
		srv.request = new DirectionRequest();
		assertSame(srv, srv.destination(3,8));
		assertEquals(3, srv.request.destination.getPoint().getLat(), 0.1);
		assertEquals(8, srv.request.destination.getPoint().getLng(), 0.1);
	}	
	
	@Test
	public void testDestinationAsPoint() {		
		Point point = new Point(3,8);
		DirectionService srv = new DirectionService();
		srv.request = new DirectionRequest();
		assertSame(srv, srv.destination(point));
		assertSame(point, srv.request.destination.getPoint());		
	}

	@Test
	public void testOriginAsPoint() {		
		Point point = new Point(3,8);
		DirectionService srv = new DirectionService();
		srv.request = new DirectionRequest();
		assertSame(srv, srv.origin(point));
		assertSame(point, srv.request.origin.getPoint());		
	}
	
	@Test
	public void testOriginAsDouble() {		
		DirectionService srv = new DirectionService();
		srv.request = new DirectionRequest();
		assertSame(srv, srv.origin(3,8));
		assertEquals(3, srv.request.origin.getPoint().getLat(), 0.1);
		assertEquals(8, srv.request.origin.getPoint().getLng(), 0.1);
	}
	
}
