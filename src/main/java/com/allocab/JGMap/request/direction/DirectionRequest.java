package com.allocab.JGMap.request.direction;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import com.allocab.JGMap.common.TravelMode;
import com.allocab.JGMap.request.AbstractRequest;
import com.allocab.JGMap.request.HttpMethod;
import com.allocab.JGMap.response.direction.DirectionResponse;
import com.allocab.JGMap.response.direction.Point;

public class DirectionRequest extends AbstractRequest<DirectionResponse> {
	
	public enum Avoid {
		tolls,
		highways,
		ferries
	}
	
	public enum Language {
		fr
	}
	
	public enum Unit {
		metric, imperial
	}
	
	public enum TLD {
		fr
	}	
	
	public String origin;
	public String destination;
	public TravelMode mode = TravelMode.DRIVING;
	public boolean alternatives;
	public Avoid avoid;
	public Language language;
	public Unit units;
	public TLD region;
	public Date departure_time;
	public Date arrival_time;
	
	public DirectionRequest() {
		this.httpMethod = HttpMethod.GET;
		this.serviceUrl = "directions";
		this.response = DirectionResponse.class;
	}
	
	public static DirectionRequest gen() {
		return new DirectionRequest();
	}
	
	public DirectionRequest origin(String origin) {
		this.origin = origin;
		return this;
	}
	
	public DirectionRequest destination(String destination) {
		this.destination = destination;
		return this;
	}
	
	public DirectionRequest destination(boolean alternatives) {
		this.alternatives = alternatives;
		return this;
	}
	
	@Override
	public String toParameters() {
		Map<String,Object> map = this.toMap();		
		StringBuilder parameters = new StringBuilder();
		int size = map.keySet().size();
		if(size > 0) {
			parameters.append("?");
		}
		StringBuilder param;
		for(String key : map.keySet()) {			
			Object value = map.get(key);			
			if(value == null) {
				continue;
			}			
			param = new StringBuilder().append(key).append("=");
			if(value instanceof String) {
				try {
					param.append(URLEncoder.encode((String)value, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					param.append(value);
				}				
			}
			else {
				param.append(value);
			}			
			param.append("&");
			
			parameters.append(param);
		}
		
		return parameters.toString();
		/*
		try {
			return URLEncoder.encode(parameters.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return parameters.toString();
		}
		*/

	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public TravelMode getMode() {
		return mode;
	}

	public void setMode(TravelMode mode) {
		this.mode = mode;
	}

	public boolean isAlternatives() {
		return alternatives;
	}

	public void setAlternatives(boolean alternatives) {
		this.alternatives = alternatives;
	}

	public Avoid getAvoid() {
		return avoid;
	}

	public void setAvoid(Avoid avoid) {
		this.avoid = avoid;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Unit getUnits() {
		return units;
	}

	public void setUnits(Unit units) {
		this.units = units;
	}

	public TLD getRegion() {
		return region;
	}

	public void setRegion(TLD region) {
		this.region = region;
	}

	public Date getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}

	public Date getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(Date arrival_time) {
		this.arrival_time = arrival_time;
	}
	
}
