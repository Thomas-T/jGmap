package com.allocab.JGMap.request;

import java.util.Map;

import com.allocab.JGMap.response.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract public class AbstractRequest<Response extends AbstractResponse> {
	@JsonIgnore
	public HttpMethod httpMethod = HttpMethod.GET;
	@JsonIgnore
	public String serviceUrl;
	@JsonIgnore
	public Class<Response> response;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> toMap() {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.convertValue(this, Map.class);
	}
	abstract public String toParameters();
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
}
