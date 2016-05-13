package com.allocab.jGmap.request;

import java.util.Date;

import com.allocab.jGmap.common.Avoid;
import com.allocab.jGmap.common.Language;
import com.allocab.jGmap.common.Location;
import com.allocab.jGmap.common.TLD;
import com.allocab.jGmap.common.TravelMode;
import com.allocab.jGmap.common.Unit;
import com.allocab.jGmap.common.serializer.DepartureTimeSerializer;
import com.allocab.jGmap.response.direction.DirectionResponse;
import com.allocab.jGmap.response.direction.Point;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.allocab.jGmap.common.serializer.LocationSerializer;

public class DirectionRequest extends AbstractRequest<DirectionResponse> {
	

	@JsonSerialize(using = LocationSerializer.class)
	public Location origin;
	@JsonSerialize(using = LocationSerializer.class)
	public Location destination;
	public TravelMode mode;
	public boolean alternatives;
	public Avoid avoid;
	public Language language;
	public Unit units;
	public TLD region;
	@JsonSerialize(using = DepartureTimeSerializer.class)	
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
	
	public DirectionRequest departure_time(Date date) {
		this.departure_time = date;
		return this;
	}
	
	public DirectionRequest origin(String origin) {
		this.origin = Location.gen().address(origin);
		return this;
	}
	
	public DirectionRequest origin(Point origin) {
		this.origin = Location.gen().point(origin);
		return this;
	}
	
	public DirectionRequest origin(double lat, double lng) {
		this.origin = Location.gen().point(lat,lng);
		return this;
	}
	
	public DirectionRequest destination(String destination) {
		this.destination = Location.gen().address(destination);
		return this;
	}
	
	public DirectionRequest destination(Point destination) {
		this.destination = Location.gen().point(destination);
		return this;
	}
	
	public DirectionRequest destination(double lat, double lng) {
		this.destination = Location.gen().point(lat, lng);
		return this;
	}
	
	public DirectionRequest alternatives(boolean alternatives) {
		this.alternatives = alternatives;
		return this;
	}


	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
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
