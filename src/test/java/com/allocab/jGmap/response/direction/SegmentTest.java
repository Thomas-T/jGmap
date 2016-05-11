package com.allocab.jGmap.response.direction;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.jGmap.common.Mesure;

public class SegmentTest {

	@Test
	public void testConstructor() {
		assertNotNull(new Segment());
	} 
	
	@Test
	public void testStartLocation() {
		Point point = new Point(9,8);
		Segment s = new Segment();
		s.setStart_location(point);
		assertSame(point, s.getStart_location());
	}
	
	
	@Test
	public void testEndLocation() {
		Point point = new Point(9,8);
		Segment s = new Segment();
		s.setEnd_location(point);
		assertSame(point, s.getEnd_location());
	}
	
	@Test
	public void testDistance() {
		Mesure mesure = new Mesure();
		Segment s = new Segment();
		s.setDistance(mesure);
		assertSame(mesure, s.getDistance());
	}
	
	
}
