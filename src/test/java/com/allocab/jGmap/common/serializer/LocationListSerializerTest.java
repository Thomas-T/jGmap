package com.allocab.jGmap.common.serializer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import com.allocab.jGmap.common.Location;
import com.allocab.jGmap.common.LocationList;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class LocationListSerializerTest {

	@Test
	public void testNullSerialization() throws IOException {
		Writer wr = new StringWriter();
		JsonGenerator json = new JsonFactory().createGenerator(wr);
		LocationListSerializer ser = new LocationListSerializer();
		ser.serialize(null, json, null);
		json.close();
		wr.close();
		assertEquals("",wr.toString());
	}
	
	@Test
	public void testWithDataSerialization() throws IOException {
		Writer wr = new StringWriter();
		JsonGenerator json = new JsonFactory().createGenerator(wr);		
		
		LocationListSerializer ser = new LocationListSerializer();
		
		LocationList ll = new LocationList();
		ll.add(Location.gen().address("caca"));
		ll.add(Location.gen().address("cacaprout"));
		
		ser.serialize(ll, json, null);
		
		json.close();
		wr.close();
		
		assertEquals("\"caca|cacaprout\"",wr.toString());
	}
	
}
