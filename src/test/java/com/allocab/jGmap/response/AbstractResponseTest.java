package com.allocab.jGmap.response;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.jGmap.response.direction.DirectionResponse;

public class AbstractResponseTest {

	@Test
	public void testNullValueToDeserialize() {
		DirectionResponse response = AbstractResponse.deserialize(DirectionResponse.class, null);
		assertNull(response);
	}
	
}
