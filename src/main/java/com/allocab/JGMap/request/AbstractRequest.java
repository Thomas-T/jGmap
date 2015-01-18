package com.allocab.JGMap.request;

import java.util.Map;

import com.allocab.JGMap.request.direction.Location;
import com.allocab.JGMap.response.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract public class AbstractRequest<Response extends AbstractResponse> {
	
	private static String apiClientId;
	private static String apiCryptoKey;
	
	@JsonIgnore
	public HttpMethod httpMethod = HttpMethod.GET;
	@JsonIgnore
	public String serviceUrl;
	@JsonIgnore
	public Class<Response> response;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> toMap() {		
		ObjectMapper objectMapper = new ObjectMapper();		
		 Map<String, Object> o = objectMapper.convertValue(this, Map.class);		 
		 if(AbstractRequest.apiClientId != null) {
			 o.put("client", AbstractRequest.apiClientId);
		 }		 
		 return o;
	}
	abstract public String toParameters();
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
	public static String getApiClientId() {
		return apiClientId;
	}
	public static void setApiClientId(String apiClientId) {
		AbstractRequest.apiClientId = apiClientId;
	}
	public static String getApiCryptoKey() {
		return apiCryptoKey;
	}
	public static void setApiCryptoKey(String apiCryptoKey) {
		AbstractRequest.apiCryptoKey = apiCryptoKey;
	}
}
