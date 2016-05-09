package com.allocab.JGMap.common;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.allocab.JGMap.response.direction.Point;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@SuppressWarnings("serial")
public class Location implements Parameterizable, Serializable {
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

  @Override
  public String toParam() {
    if(this.getPoint() != null) {
      return this.getPoint().toParam();
    }
    try {
      return URLEncoder.encode(this.getAddress(), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }
}
