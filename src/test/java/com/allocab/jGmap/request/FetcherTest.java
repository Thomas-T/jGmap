package com.allocab.jGmap.request;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.jGmap.request.direction.DirectionRequest;
import com.allocab.jGmap.response.direction.DirectionResponse;

public class FetcherTest {

	public void testGetPayload() {
		Fetcher<DirectionResponse, DirectionRequest> f = new Fetcher<>(null);
		Object o = new Object();
		f.setPayload(o);
		assertSame(o, f.getPayload());
	}	
	
	public void testGetMethod() {
		Fetcher<DirectionResponse, DirectionRequest> f = new Fetcher<>(null);
		f.setMethod(HttpMethod.DELETE);
		assertEquals(HttpMethod.DELETE, f.getMethod());
	}
	
	public void testGetStatus() {
		Fetcher<DirectionResponse, DirectionRequest> f = new Fetcher<>(null);
		f.setStatus(Fetcher.Status.FETCH_ERROR);
		assertEquals(Fetcher.Status.FETCH_ERROR, f.getStatus());
	}
	
	public void testGetResponseString() {
		Fetcher<DirectionResponse, DirectionRequest> f = new Fetcher<>(null);
		f.setResponseString("caca");
		assertEquals("caca", f.getResponseString());
	}

	@Test
	public void testGenCryptedUrl() {
		String url = Fetcher.genUrl("http://ron.dou.dou.fr/$SERVICE$", "miaous", "?poke=mon", "teamrocket");
		assertNotNull(url);
		assertEquals("http://ron.dou.dou.fr/miaous?poke=mon&signature=qYmEvOwtz7qVhXadc01zjhuUreM=", url);
	}	
	
	@Test
	public void testGenBadUrl() {
		String url = Fetcher.genUrl("htttp://ron.dou.dou.fr/$SERVICE$", "miaous", "?poke=mon", null);
		assertNull(url);
	}
	
	@Test
	public void testGenBadUrl2() {
		String url = Fetcher.genUrl("http://ron.dou.dou.fr/$SERVICE$", null, "?poke=mon", null);
		assertNull(url);
	}

	@Test
	public void testGenUrl() {
		String url = Fetcher.genUrl("http://ron.dou.dou.fr/$SERVICE$", "miaous", "?poke=mon", null);
		assertNotNull(url);
		assertEquals("http://ron.dou.dou.fr/miaous?poke=mon", url);
	}
	
	
	@Test
	public void testErrorOnCall() {
		Fetcher<DirectionResponse, DirectionRequest> f = new Fetcher<>(null);
		assertNull(f.call());
	}
	
	@Test
	public void testErrorOnCallWhenBuildingURL() {
		Fetcher<DirectionResponse, DirectionRequest> f = new Fetcher<>(null);
		f.request = new DirectionRequest();
		f.request.serviceUrl = null;
		assertNull(f.call());
	}
	
}
