package com.allocab.jGmap.service;


public interface Service<Request,Response> {
  public Response call();  
  public Request getRequest();
  public void setRequest(Request request);
}
