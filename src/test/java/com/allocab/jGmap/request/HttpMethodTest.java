package com.allocab.jGmap.request;

import static org.junit.Assert.*;

import org.junit.Test;

public class HttpMethodTest {

	@Test
	public void testToString() {
		assertEquals("GET", HttpMethod.GET.toString());
		assertEquals("POST", HttpMethod.POST.toString());
		assertEquals("DELETE", HttpMethod.DELETE.toString());
		assertEquals("PUT", HttpMethod.PUT.toString());
	}
	
}
