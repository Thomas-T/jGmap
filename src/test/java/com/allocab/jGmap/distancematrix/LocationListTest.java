package com.allocab.jGmap.distancematrix;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.jGmap.common.Location;
import com.allocab.jGmap.request.distancematrix.LocationList;

public class LocationListTest {

	@Test
	public void testLocationListWithOneLocationData() {
		LocationList list = new LocationList();
		list.add(Location.gen().address("prout"));
		assertEquals("prout", list.toParam());		
	}
	
	@Test
	public void testLocationListWithTwoLocationData() {
		LocationList list = new LocationList();
		list.add(Location.gen().address("prout"));
		list.add(Location.gen().address("caca"));
		assertEquals("prout|caca", list.toParam());		
	}
	
	@Test
	public void testLocationListEmpty() {
		LocationList list = new LocationList();
		assertEquals("", list.toParam());
	}
}
