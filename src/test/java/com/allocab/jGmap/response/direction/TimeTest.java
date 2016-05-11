package com.allocab.jGmap.response.direction;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class TimeTest {

	@Test
	public void testValue() {
		Date date = new Date();
		Time time = new Time();
		time.setValue(date);
		assertEquals(date.getTime(), time.getValue().getTime());
	}
	
	@Test
	public void testText() {
		Time time = new Time();
		time.setText("yo");
		assertEquals("yo", time.getText());
	}
	
	@Test
	public void testTimeZone() {
		Time time = new Time();
		time.setTime_zone("yo");
		assertEquals("yo", time.getTime_zone());
	}
	
}
