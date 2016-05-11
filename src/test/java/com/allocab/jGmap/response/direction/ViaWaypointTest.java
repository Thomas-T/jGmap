package com.allocab.jGmap.response.direction;

import static org.junit.Assert.*;

import org.junit.Test;

public class ViaWaypointTest {

	@Test
	public void testGetLocation() {
		Point point = new Point(9,3);
		ViaWaypoint wp = new ViaWaypoint();
		wp.setLocation(point);
		assertSame(point, wp.getLocation());
	}
	
	@Test
	public void testGetStepIndex() {
		ViaWaypoint wp = new ViaWaypoint();
		wp.setStep_index(1);
		assertEquals(1, wp.getStep_index());
	}
	
	@Test
	public void testGetStepInterpolation() {
		ViaWaypoint wp = new ViaWaypoint();
		wp.setStep_interpolation(1.2d);
		assertEquals(1.2d, wp.getStep_interpolation(), 0.1);
	}
	
}
