package com.allocab.jGmap.service;

import java.util.Date;

import com.allocab.jGmap.request.Fetcher;
import com.allocab.jGmap.request.direction.DirectionRequest;
import com.allocab.jGmap.response.direction.DirectionResponse;
import com.allocab.jGmap.response.direction.Point;

public class DirectionService implements Service<DirectionRequest, DirectionResponse> {
	
	private DirectionRequest request;
	
	public static DirectionService gen() {
		DirectionService service = new DirectionService();		
		service.request = DirectionRequest.gen();		
		return service;
	}
	
	@Override
	public DirectionResponse call() {
		Fetcher<DirectionResponse, DirectionRequest> fetcher = Fetcher.gen(request);
		return fetcher.call();
	}

	public DirectionService departure_time(Date date) {
		this.request.departure_time(date);
		return this;
	}	
	
	public DirectionService origin(Point origin) {
		this.request.origin(origin);
		return this;
	}
	
	public DirectionService origin(String origin) {
		this.request.origin(origin);
		return this;
	}
	
	public DirectionService origin(double lat, double lng) {
		this.request.origin(lat, lng);
		return this;
	}
	
	public DirectionService destination(Point destination) {
		this.request.destination(destination);
		return this;
	}
	
	public DirectionService destination(String destination) {
		this.request.destination(destination);
		return this;
	}
	
	public DirectionService destination(double lat, double lng) {
		this.request.destination(lat, lng);
		return this;
	}

	@Override
	public DirectionRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(DirectionRequest request) {
		this.request = request;
	}

	
}
