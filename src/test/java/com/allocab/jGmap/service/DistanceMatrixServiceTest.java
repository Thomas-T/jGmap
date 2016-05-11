package com.allocab.jGmap.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.allocab.jGmap.common.Language;
import com.allocab.jGmap.common.TravelMode;
import com.allocab.jGmap.common.Unit;
import com.allocab.jGmap.request.distancematrix.DistanceMatrixRequest;

public class DistanceMatrixServiceTest {

	@Test
	public void testMode() {
		DistanceMatrixService srv = new DistanceMatrixService();
		srv.request = new DistanceMatrixRequest();
		assertSame(srv, srv.mode(TravelMode.WALKING));
		assertEquals(TravelMode.WALKING, srv.request.mode);
	}	
	
	@Test
	public void testLanguage() {
		DistanceMatrixService srv = new DistanceMatrixService();
		srv.request = new DistanceMatrixRequest();
		assertSame(srv, srv.language(Language.fr));
		assertEquals(Language.fr, srv.request.language);
	}
	
	@Test
	public void testUnits() {
		DistanceMatrixService srv = new DistanceMatrixService();
		srv.request = new DistanceMatrixRequest();
		assertSame(srv, srv.units(Unit.imperial));
		assertEquals(Unit.imperial, srv.request.units);
	}
	
	@Test
	public void testArrivalTime() {
		Date arrivalTime = new Date();
		
		DistanceMatrixService srv = new DistanceMatrixService();
		srv.request = new DistanceMatrixRequest();
		assertSame(srv, srv.arrivalTime(arrivalTime));
		assertEquals(arrivalTime.getTime(), srv.request.arrival_time.getTime());
	}

	@Test
	public void testSetAndGetRequest() {
		DistanceMatrixRequest req = new DistanceMatrixRequest();
		DistanceMatrixService srv = new DistanceMatrixService();
		srv.setRequest(req);
		assertSame(req, srv.getRequest());
	}
}
