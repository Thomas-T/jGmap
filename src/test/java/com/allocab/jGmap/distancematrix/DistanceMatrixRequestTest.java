package com.allocab.jGmap.distancematrix;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.allocab.jGmap.common.Language;
import com.allocab.jGmap.common.Location;
import com.allocab.jGmap.common.Parameterizable;
import com.allocab.jGmap.common.TravelMode;
import com.allocab.jGmap.common.Unit;
import com.allocab.jGmap.request.DistanceMatrixRequest;

public class DistanceMatrixRequestTest {
	
	@Test
	public void testAddDestination() {
		Location location = Location.gen().address("Empire State Building");
		DistanceMatrixRequest req = DistanceMatrixRequest.gen();
		assertSame(req, req.addDestination(location));
		assertSame(location, req.destinations.get(0));
	}
	
	@Test
	public void testAddOrigin() {
		Location location = Location.gen().address("Empire State Building");
		DistanceMatrixRequest req = DistanceMatrixRequest.gen();
		assertSame(req, req.addOrigin(location));
		assertSame(location, req.origins.get(0));
	}
	
	@Test
	public void testArrivalTime() {
		Date arrivalTime = new Date();
		DistanceMatrixRequest req = DistanceMatrixRequest.gen();
		assertSame(req, req.arrivalTime(arrivalTime));
		assertEquals(arrivalTime.getTime(), req.arrival_time.getTime());	
	}
	
	@Test
	public void testDepartureTime() {
		Date departureTime = new Date();
		DistanceMatrixRequest req = DistanceMatrixRequest.gen();
		assertSame(req, req.departureTime(departureTime));
		assertEquals(departureTime.getTime(), req.departure_time.getTime());	
	}
	
	@Test
	public void testUnits() {		
		DistanceMatrixRequest req = DistanceMatrixRequest.gen();
		assertSame(req, req.units(Unit.metric));
		assertEquals(Unit.metric, req.units);	
	}
	
	@Test
	public void testLanguage() {		
		DistanceMatrixRequest req = DistanceMatrixRequest.gen();
		assertSame(req, req.language(Language.fr));
		assertEquals(Language.fr, req.language);	
	}
	
	@Test
	public void testMode() {		
		DistanceMatrixRequest req = DistanceMatrixRequest.gen();
		assertSame(req, req.mode(TravelMode.TRANSIT));
		assertEquals(TravelMode.TRANSIT, req.mode);	
	}
  
  @Test
  public void testParameterizableTest() {
    assertTrue(new DistanceMatrixRequest().origins instanceof Parameterizable);
  }

  @Test
  public void testEmptyRequestSerialization() {
    DistanceMatrixRequest req = new DistanceMatrixRequest();
    String params = req.toParameters();
    assertNotNull(params);
    assertEquals("", params);
  }
  
  @Test
  public void testBasicRequestSerialization() {
    DistanceMatrixRequest req = new DistanceMatrixRequest();
    
    req.mode = TravelMode.DRIVING;
    req.units = Unit.metric;
    req.language = Language.fr;        
    
    String params = req.toParameters();
    assertNotNull(params);
    assertEquals("?mode=driving&language=fr&units=metric", params);
  }
  
  @Test
  public void testWithDepartureTimeRequestNowSerialization() {
    DistanceMatrixRequest req = new DistanceMatrixRequest();
    
    Date departureTime = new Date(1462798805972l);
    
    req.mode = TravelMode.BICYCLING;
    req.units = Unit.imperial;
    req.language = Language.fr;  
    req.departure_time = departureTime;
    
    String params = req.toParameters();
    assertNotNull(params);
    assertEquals("?mode=bicycling&language=fr&units=imperial&departure_time=now", params);
  }
  
  @Test
  public void testWithDepartureTimeRequestOneHourSerialization() {
    DistanceMatrixRequest req = new DistanceMatrixRequest();
    
    
    Date departureTime = new Date(new Date().getTime() + (1000*60*60));
    long t = departureTime.getTime();
    
    req.mode = TravelMode.DRIVING;
    req.units = Unit.imperial;
    req.language = Language.fr;  
    req.departure_time = departureTime;
    
    String params = req.toParameters();
    assertNotNull(params);
    assertEquals("?mode=driving&language=fr&units=imperial&departure_time="+t, params);
  }
  
  @Test
  public void testWithArrivalTimeRequestOneHourSerialization() {
    DistanceMatrixRequest req = new DistanceMatrixRequest();
    
    
    Date arrivalTime = new Date(new Date().getTime() + (1000*60*60));
    long t = arrivalTime.getTime();
    
    req.mode = TravelMode.DRIVING;
    req.units = Unit.imperial;
    req.language = Language.fr;  
    req.arrival_time = arrivalTime;
    
    String params = req.toParameters();
    assertNotNull(params);
    System.out.println(params);
    assertEquals("?mode=driving&language=fr&units=imperial&arrival_time="+t, params);
  }
  
  @Test
  public void testWithOneDestinationAndOneOriginSerialization() {
    DistanceMatrixRequest req = new DistanceMatrixRequest();
    
    req.mode = TravelMode.DRIVING;
    req.units = Unit.imperial;
    req.language = Language.fr;  
    
    req.origins.add(Location.gen().address("11 rue de l'annonciade, 69001 Lyon, France"));
    req.destinations.add(Location.gen().address("102 avenue maréchal de Saxe 69003 Lyon, France"));
    
    
    String params = req.toParameters();
    assertNotNull(params);
    System.out.println(params);
    assertEquals("?origins=11+rue+de+l%27annonciade%2c+69001+lyon%2c+france&destinations=102+avenue+mar%c3%a9chal+de+saxe+69003+lyon%2c+france&mode=driving&language=fr&units=imperial", params);
  }
  
  @Test
  public void testWithTwoDestinationAndOneOriginSerialization() {
    DistanceMatrixRequest req = new DistanceMatrixRequest();
    
    req.mode = TravelMode.DRIVING;
    req.units = Unit.imperial;
    req.language = Language.fr;  
    
    req.origins.add(Location.gen().address("11 rue de l'annonciade, 69001 Lyon, France"));
    req.origins.add(Location.gen().address("102 avenue maréchal de Saxe 69003 Lyon, France"));
    req.destinations.add(Location.gen().address("102 avenue maréchal de Saxe 69003 Lyon, France"));
    
    
    String params = req.toParameters();
    assertNotNull(params);
    System.out.println(params);
    assertEquals("?origins=11+rue+de+l%27annonciade%2c+69001+lyon%2c+france|102+avenue+mar%c3%a9chal+de+saxe+69003+lyon%2c+france&destinations=102+avenue+mar%c3%a9chal+de+saxe+69003+lyon%2c+france&mode=driving&language=fr&units=imperial", params);
  }
  
  
  
}
