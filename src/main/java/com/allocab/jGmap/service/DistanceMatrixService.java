package com.allocab.jGmap.service;

import java.util.Date;

import com.allocab.jGmap.common.Language;
import com.allocab.jGmap.common.Location;
import com.allocab.jGmap.common.TravelMode;
import com.allocab.jGmap.common.Unit;
import com.allocab.jGmap.request.Fetcher;
import com.allocab.jGmap.request.distancematrix.DistanceMatrixRequest;
import com.allocab.jGmap.response.distancematrix.DistanceMatrixResponse;

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

  public DistanceMatrixService addDestination(Location location) {
    this.request.destinations.add(location);
    return this;
  }
  
  public DistanceMatrixService addDestination(String address) {
    this.addDestination(Location.gen().address(address));
    return this;
  }
  
  public DistanceMatrixService addOrigin(Location location) {
    this.request.origins.add(location);
    return this;
  }
  
  public DistanceMatrixService addOrigin(String address) {
    this.addOrigin(Location.gen().address(address));
    return this;
  }
  
  public DistanceMatrixService arrivalTime(Date arrivalTime) {
    this.request.arrival_time = arrivalTime;
    return this;
  }
  
  public DistanceMatrixService departureTime(Date departureTime) {
    this.request.departure_time = departureTime;
    return this;
  }
  
  public DistanceMatrixService units(Unit units) {
    this.request.units = units;
    return this;
  }
  
  public DistanceMatrixService language(Language language) {
    this.request.language = language;
    return this;
  }
  
  public DistanceMatrixService mode(TravelMode mode) {
    this.request.mode = mode;
    return this;
  }  

}
