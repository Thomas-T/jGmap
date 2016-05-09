package com.allocab.JGMap.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.allocab.JGMap.common.Location;
import com.allocab.JGMap.common.Parameterizable;
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
      if(value == null || value.equals("")) {
        continue;
      } 
      if(value instanceof List<?>) {
        List<?> list = (List<?>) value;
        if(list.size() == 0) {
          continue;
        }
      }
      param = new StringBuilder().append(key).append("=");
      param.append(value);
      param.append("&");
      
      parameters.append(param);
    }
    
    String params = parameters.toString();
    if(params.endsWith("&")) {
      params = params.substring(0, params.length()-1);
    }
    
    if(params.equals("?")) {
      return "";
    }
    
    return params.toLowerCase();
  }
	
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
