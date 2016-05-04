package com.allocab.JGMap.service;

import com.allocab.JGMap.request.Fetcher;
import com.allocab.JGMap.request.distancematrix.DistanceMatrixRequest;
import com.allocab.JGMap.response.distancematrix.DistanceMatrixResponse;

public class DistanceMatrixService implements Service<DistanceMatrixRequest, DistanceMatrixResponse> {

  
  private DistanceMatrixRequest request;
  
  public static DistanceMatrixService gen() {
    DistanceMatrixService service = new DistanceMatrixService();    
    service.request = DistanceMatrixRequest.gen();   
    return service;
  }
  
  @Override
  public DistanceMatrixResponse call() {
    Fetcher<DistanceMatrixResponse, DistanceMatrixRequest> fetcher = Fetcher.gen(request);
    return fetcher.call();
  }

  @Override
  public DistanceMatrixRequest getRequest() {
    return this.request;
  }

  @Override
  public void setRequest(DistanceMatrixRequest request) {
    this.request = request;
  }


}
