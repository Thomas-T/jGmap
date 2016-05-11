package com.allocab.jGmap.response.direction;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.jGmap.common.Mesure;
import com.allocab.jGmap.response.direction.DirectionResponse.Comparison;

public class DirectionResponseTest {

	@Test
	public void testGetShortestRouteWhenRoutesAreNull() {
		DirectionResponse res = new DirectionResponse();
		assertNull(res.getShortestRoute(Comparison.DISTANCE));
	}
	
	@Test
	public void testGetLongestRouteWhenRoutesAreNull() {
		DirectionResponse res = new DirectionResponse();
		assertNull(res.getLongestRoute(Comparison.DISTANCE));
	}
	
	@Test
	public void testGetLongestRouteWhenOnlyOneRoute() {
		DirectionResponse res = new DirectionResponse();
		Route route = new Route();
		res.setRoutes(new Route[]{ route });
		assertSame(route, res.getLongestRoute(Comparison.DISTANCE));
	}
	
	@Test
	public void testGetShortestRouteByDuration() {
		Route shortest = new Route();
		Mesure shortestMesure = new Mesure();
		shortestMesure.setValue(10);
		shortest.setDuration(shortestMesure);
		
		Route longest = new Route();
		Mesure longestMesure = new Mesure();
		longestMesure.setValue(1000);
		longest.setDuration(longestMesure);
		
		DirectionResponse res = new DirectionResponse();
		res.setRoutes(new Route[]{ longest, shortest, shortest, longest });
		assertSame(shortest, res.getShortestRoute(Comparison.DURATION));
	}
	
	@Test
	public void testGetLongestRouteByDuration() {
		Route shortest = new Route();
		Mesure shortestMesure = new Mesure();
		shortestMesure.setValue(10);
		shortest.setDuration(shortestMesure);
		
		Route longest = new Route();
		Mesure longestMesure = new Mesure();
		longestMesure.setValue(1000);
		longest.setDuration(longestMesure);
		
		DirectionResponse res = new DirectionResponse();
		res.setRoutes(new Route[]{ shortest, longest, shortest, shortest, longest });
		assertSame(longest, res.getLongestRoute(Comparison.DURATION));
	}
	
	
	@Test
	public void testGetShortestRouteByDistance() {
		Route shortest = new Route();
		Mesure shortestMesure = new Mesure();
		shortestMesure.setValue(10);
		shortest.setDistance(shortestMesure);
		
		Route longest = new Route();
		Mesure longestMesure = new Mesure();
		longestMesure.setValue(1000);
		longest.setDistance(longestMesure);
		
		DirectionResponse res = new DirectionResponse();
		res.setRoutes(new Route[]{ longest, shortest, shortest, longest });
		assertSame(shortest, res.getShortestRoute(Comparison.DISTANCE));
	}
	
	@Test
	public void testGetLongestRouteByDistance() {
		Route shortest = new Route();
		Mesure shortestMesure = new Mesure();
		shortestMesure.setValue(10);
		shortest.setDistance(shortestMesure);
		
		Route longest = new Route();
		Mesure longestMesure = new Mesure();
		longestMesure.setValue(1000);
		longest.setDistance(longestMesure);
		
		DirectionResponse res = new DirectionResponse();
		res.setRoutes(new Route[]{ shortest, longest, shortest, shortest, longest });
		assertSame(longest, res.getLongestRoute(Comparison.DISTANCE));
	}
	
	
	@Test
	public void testGetShortestRouteByDurationInTraffic() {
		Route shortest = new Route();
		Mesure shortestMesure = new Mesure();
		shortestMesure.setValue(10);
		shortest.setDuration_in_traffic(shortestMesure);
		
		Route longest = new Route();
		Mesure longestMesure = new Mesure();
		longestMesure.setValue(1000);
		longest.setDuration_in_traffic(longestMesure);
		
		DirectionResponse res = new DirectionResponse();
		res.setRoutes(new Route[]{ longest, shortest, shortest, longest });
		assertSame(shortest, res.getShortestRoute(Comparison.DURATION_IN_TRAFFIC));
	}
	
	@Test
	public void testGetLongestRouteByDurationInTraffic() {
		Route shortest = new Route();
		Mesure shortestMesure = new Mesure();
		shortestMesure.setValue(10);
		shortest.setDuration_in_traffic(shortestMesure);
		
		Route longest = new Route();
		Mesure longestMesure = new Mesure();
		longestMesure.setValue(1000);
		longest.setDuration_in_traffic(longestMesure);
		
		DirectionResponse res = new DirectionResponse();
		res.setRoutes(new Route[]{ shortest, longest, shortest, shortest, longest });
		assertSame(longest, res.getLongestRoute(Comparison.DURATION_IN_TRAFFIC));
	}
	
}
