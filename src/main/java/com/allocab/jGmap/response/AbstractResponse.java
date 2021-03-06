package com.allocab.jGmap.response;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SuppressWarnings("serial")
public abstract class AbstractResponse implements Serializable {

  public enum Status {
    OK,
    NOT_FOUND,
    ZERO_RESULTS,
    MAX_WAYPOINTS_EXCEEDED,
    INVALID_REQUEST,
    OVER_QUERY_LIMIT,
    REQUEST_DENIED,
    UNKNOWN_ERROR
  }
  
  private Status status;  

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
  
  protected static <T extends AbstractResponse> T deserialize(Class<T> clazz, String response) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);    
    try {
      return objectMapper.readValue(response, clazz);
    } catch (Exception e) {
      return null;
    }
  }
  
  public String serialize() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    StringWriter str = new StringWriter();
    objectMapper.writeValue(str, this);
    return str.toString();
  }
  
}
