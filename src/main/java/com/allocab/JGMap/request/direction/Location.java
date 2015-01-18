package com.allocab.JGMap.request.direction;

import java.io.IOException;

import com.allocab.JGMap.response.direction.Point;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class Location {
	private Point point;
	private String address;
	
	public static Location gen() {
		return new Location();
	}
	
	public Location point(Point point) {
		this.point = point;
		return this;
	}
	
	public Location point(double lat, double lng) {
		this.point = new Point(lat, lng);
		return this;
	}
	
	public Location address(String address) {
		this.address = address;
		return this;
	}
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public static class LocationSerializer extends JsonSerializer<Location> {


		@Override
		public void serialize(Location value, JsonGenerator jgen, SerializerProvider provider)
		        throws IOException, JsonGenerationException {
		    if(value.getPoint() != null) {
		    	jgen.writeString(value.getPoint().getLat()+","+value.getPoint().getLng());
		    }
		    else {
		    	jgen.writeString(value.getAddress());
		    }
		    
		}

	}
}
