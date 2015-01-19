package com.allocab.JGMap.direction;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.allocab.JGMap.ResourceLoader;
import com.allocab.JGMap.response.AbstractResponse;
import com.allocab.JGMap.response.direction.DirectionResponse;
import com.allocab.JGMap.response.direction.DirectionResponse.Comparison;
import com.allocab.JGMap.response.direction.Leg;
import com.allocab.JGMap.response.direction.Point;
import com.allocab.JGMap.response.direction.Route;
import com.allocab.JGMap.response.direction.Step;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class TestDirectionResponse {
  
  
  @Test
  public void testSegmentRetievalAfterDirectionEnded() throws Exception {
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction_alternatives.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);    
    
    Date departureAt = new Date();
    DateTime dt = new DateTime().plusHours(2);
    
    Point point = response.getShortestRoute(Comparison.DISTANCE).getPointAtForDepartureAt(Comparison.DURATION, dt.toDate(), departureAt);
    assertEquals(48.8897321d, point.getLat(),0.001);
    assertEquals(2.2418562d, point.getLng(),0.001);     
  }    
  
  
  @Test
  public void testBuildingCompleteDirection() throws JsonGenerationException, JsonMappingException, IOException {
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction_alternatives.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    
    Date departureAt = new Date();    
    
    Route route = response.getShortestRoute(Comparison.DISTANCE);
    
    List<Point> points = new ArrayList<Point>();
    
    DateTime dt = new DateTime();
    boolean running = true;
    
    int nbSame = 0;
    Point previous = null;
    
    while(running) {
      Point point = route.getPointAtForDepartureAt(Comparison.DURATION, dt.toDate(), departureAt);
      points.add(point);
      if(previous != null) {
        if(previous.getLat() == point.getLat() && previous.getLng() == point.getLng()) {
          nbSame++;
        }
        else {
          nbSame = 0;
        }
        
        if(nbSame > 30) {
          running = false;
        }        
      }
      previous = point;
      dt = dt.plusSeconds(1);
    }
    
    assertEquals(1985, points.size());    
  }

  @Test
  public void testPointRetrieval() {
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction_alternatives.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    
    Date departureAt = new Date();
    
    DateTime questionAt = new DateTime(departureAt);
    
    Route route = response.getShortestRoute(Comparison.DISTANCE);
    
    Point p = route.getPointAtForDepartureAt(Comparison.DURATION, questionAt.toDate(), departureAt);
    assertNotNull(p);
  }  
  
  @Test
  public void testSegmentCreation() {
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction_alternatives.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    
    Date departureAt = new Date();
    
    DateTime questionAt = new DateTime(departureAt);
    
    Route route = response.getShortestRoute(Comparison.DISTANCE);
    
    Step step = route.getStepAtForDepartureAt(Comparison.DURATION, questionAt.toDate(), departureAt);
    
    step.buildSegments();
    assertNotNull(step.getSegments());
    
  }  
  
  
  private List<Point> getDirectionStepPoints(Route route, List<Long> secondsInterval) {

    List<Point> points = new ArrayList<Point>();
    
    Date departureAt = new Date();    
    DateTime questionAt = new DateTime(departureAt);
    
    for(Long interval : secondsInterval) {
      questionAt = questionAt.plusSeconds(interval.intValue());
      
      Step step = route.getStepAtForDepartureAt(Comparison.DURATION, questionAt.toDate(), departureAt);
      assertNotNull(step);
      
      points.add(step.getStart_location());            
    }
    
    return points;
  } 
  

  @Test
  public void testGetStepAt() {
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction_alternatives.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    
    Route route = response.getShortestRoute(Comparison.DISTANCE);
    
    List<Long> intervals = new ArrayList<Long>();
    
    intervals.add(0l);
    intervals.add(124l);
    intervals.add(144l);
    intervals.add(27l);
    intervals.add(91l);
    intervals.add(15l);
    intervals.add(146l);
    intervals.add(287l);
    intervals.add(69l);
    intervals.add(656l);
    intervals.add(168l);
    intervals.add(62l);
    intervals.add(23l);
    intervals.add(66l);
    intervals.add(62l);
    intervals.add(8l);
    intervals.add(6l);
    
    List<Point> points = this.getDirectionStepPoints(route, intervals);
    
    assertEquals("48.7451855,2.4013764", points.get(0).getLat()+","+points.get(0).getLng());
    assertEquals("48.745151,2.385991", points.get(1).getLat()+","+points.get(1).getLng());
    assertEquals("48.7461725,2.3693794", points.get(2).getLat()+","+points.get(2).getLng());
    assertEquals("48.7452724,2.3659238", points.get(3).getLat()+","+points.get(3).getLng());
    assertEquals("48.73914509999999,2.3653648", points.get(4).getLat()+","+points.get(4).getLng());
    assertEquals("48.7396705,2.3643142", points.get(5).getLat()+","+points.get(5).getLng());
    assertEquals("48.7697619,2.3452602", points.get(6).getLat()+","+points.get(6).getLng());
    assertEquals("48.8164342,2.3368205", points.get(7).getLat()+","+points.get(7).getLng());
    assertEquals("48.8195524,2.3281696", points.get(8).getLat()+","+points.get(8).getLng());
    assertEquals("48.8751861,2.2767188", points.get(9).getLat()+","+points.get(9).getLng());
    assertEquals("48.8832205,2.2655102", points.get(10).getLat()+","+points.get(10).getLng());
    assertEquals("48.8872868,2.2528854", points.get(11).getLat()+","+points.get(11).getLng());
    assertEquals("48.8883736,2.2512308", points.get(12).getLat()+","+points.get(12).getLng());
    assertEquals("48.8928002,2.2452456", points.get(13).getLat()+","+points.get(13).getLng());
    assertEquals("48.8896564,2.2411439", points.get(14).getLat()+","+points.get(14).getLng());
    assertEquals("48.889367,2.2416149", points.get(15).getLat()+","+points.get(15).getLng());
    
    
    intervals = new ArrayList<Long>();    
    intervals.add(new Long((124 * 60 * 2)));
    points = this.getDirectionStepPoints(route, intervals);
    assertEquals("48.889367,2.2416149", points.get(0).getLat()+","+points.get(0).getLng());
  }  
  
  @Test
  public void testPolylineDecoder() {
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    
    List<Point> points = response.getRoutes()[0].getOverview_polyline().decode();
    assertNotNull(points);
    assertEquals(231, points.size());
  }

  @Test
  public void testResponseWithWaypoints() {
    
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    assertNotNull(response);
    assertEquals(AbstractResponse.Status.OK, response.getStatus());
        
    Route[] routes = response.getRoutes();
    assertEquals(1, routes.length);
    
    Leg[] legs = routes[0].getLegs();
    assertEquals(1, legs.length);
    
    Step[] steps = legs[0].getSteps();
    assertEquals(13, steps.length);
    
  }   
  
  @Test
  public void testResponseParsingWithWaypoints() {
    
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction_waypoints.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    assertNotNull(response);
    assertEquals(AbstractResponse.Status.OK, response.getStatus());
        
    Route[] routes = response.getRoutes();
    assertEquals(1, routes.length);
    
    Leg[] legs = routes[0].getLegs();
    assertEquals(3, legs.length);
    
    Step[] steps = legs[0].getSteps();
    assertEquals(15, steps.length);
    
  } 
  
  @Test
  public void testResponseParsingMultipleRoutes() {    
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction_alternatives.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    assertNotNull(response);
    assertEquals(AbstractResponse.Status.OK, response.getStatus());
        
    Route[] routes = response.getRoutes();
    assertEquals(3, routes.length);
    
    Leg[] legs = routes[0].getLegs();
    assertEquals(1, legs.length);
    Step[] steps = legs[0].getSteps();
    assertEquals(16, steps.length);
    
    legs = routes[1].getLegs();
    assertEquals(1, legs.length);
    steps = legs[0].getSteps();
    assertEquals(12, steps.length);
    
    legs = routes[2].getLegs();
    assertEquals(1, legs.length);
    steps = legs[0].getSteps();
    assertEquals(18, steps.length);    
  } 
  
  @Test
  public void testResponseSerialization() throws Exception {
    
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    
    assertNotNull(response.serialize());
    
  } 
  
  @Test
  public void testResponseSerializationWaypoints() throws Exception {
    
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction_waypoints.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    
    assertNotNull(response.serialize());
    
  } 
  
  @Test
  public void testGetShortestRouteOneRoute() throws Exception {
    
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    
    assertNotNull(response.getShortestRoute(Comparison.DISTANCE));
    assertNotNull(response.getShortestRoute(Comparison.DURATION));
    assertNotNull(response.getShortestRoute(Comparison.DURATION_IN_TRAFFIC));
    
  } 
  
  @Test
  public void testGetShortestRouteMultipleRoutes() throws Exception {
    
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/direction_alternatives.json");
    DirectionResponse response = DirectionResponse.deserialize(directionStr);
    
    assertNotNull(response.getShortestRoute(Comparison.DISTANCE));
    assertNotNull(response.getShortestRoute(Comparison.DURATION));
    assertNotNull(response.getShortestRoute(Comparison.DURATION_IN_TRAFFIC));
    
    for(Route route : response.getRoutes()) {
      assertNotEquals(0, route.getDuration().getValue());
      assertNotEquals(0, route.getDistance().getValue());
      assertEquals(0, route.getDuration_in_traffic().getValue());
    }
    
    assertEquals(response.getRoutes()[0], response.getShortestRoute(Comparison.DISTANCE));
    assertEquals(response.getRoutes()[0], response.getShortestRoute(Comparison.DURATION));    
  } 
  
  

}
