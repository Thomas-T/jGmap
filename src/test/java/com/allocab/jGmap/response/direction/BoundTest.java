package com.allocab.jGmap.response.direction;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoundTest {

	
	@Test
	public void testSetNorthWest() {
		Point point = new Point(3,4);
		Bound bound = new Bound();
		bound.setNorthwest(point);
		assertSame(point, bound.getNorthwest());
	}
	
	@Test
	public void testSetSouthEast() {
		Point point = new Point(3,4);
		Bound bound = new Bound();
		bound.setSoutheast(point);
		assertSame(point, bound.getSoutheast());
	}
}
