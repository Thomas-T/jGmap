package com.allocab.JGMap.response;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

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
    try {
      return objectMapper.readValue(response, clazz);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
  public String serialize() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    StringWriter str = new StringWriter();
    objectMapper.writeValue(str, this);
    return str.toString();
  }
  
}
