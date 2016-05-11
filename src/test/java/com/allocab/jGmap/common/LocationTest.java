package com.allocab.jGmap.common;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.jGmap.response.direction.Point;

public class LocationTest {

	@Test
	public void testPoint() {
		Point point = new Point(4,8);
		Location location = new Location();
		location.setPoint(point);
		assertSame(point, location.getPoint());
	}
	
	@Test
	public void testAddress() {
		Location location = new Location();
		location.setAddress("caca");
		assertEquals("caca", location.getAddress());
	}
	
	@Test
	public void testToParamWithPoint() {
		Point point = new Point(4,8);
		Location location = new Location();
		location.setPoint(point);
		assertEquals("4.0,8.0", location.toParam());
	}
	
	@Test
	public void testToParamWithAddress() {
		Location location = new Location();
		location.setAddress("102 avenue maréchal");
		assertEquals("102+avenue+mar%C3%A9chal", location.toParam());
	}
	
	@Test
	public void testToParamReturnNull() {
		Location location = new Location();
		assertNull(location.toParam());
	}
	
}
