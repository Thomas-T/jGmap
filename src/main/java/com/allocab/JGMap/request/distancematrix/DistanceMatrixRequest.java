package com.allocab.JGMap.request.distancematrix;

import java.util.Date;

import com.allocab.JGMap.common.Language;
import com.allocab.JGMap.common.Location;
import com.allocab.JGMap.common.TravelMode;
import com.allocab.JGMap.common.Unit;
import com.allocab.JGMap.common.serializer.DepartureTimeSerializer;
import com.allocab.JGMap.common.serializer.LocationListSerializer;
import com.allocab.JGMap.request.AbstractRequest;
import com.allocab.JGMap.request.HttpMethod;
import com.allocab.JGMap.response.direction.DirectionResponse;
import com.allocab.JGMap.response.distancematrix.DistanceMatrixResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class DistanceMatrixRequest extends AbstractRequest<DistanceMatrixResponse>{
  
  @JsonSerialize(using = LocationListSerializer.class) 
  public LocationList origins = new LocationList();
  @JsonSerialize(using = LocationListSerializer.class)
  public LocationList destinations = new LocationList();
  
  public TravelMode mode;
  public Language language;
  public Unit units;
  @JsonSerialize(using = DepartureTimeSerializer.class) 
  public Date departure_time;
  public Date arrival_time;
  
  public DistanceMatrixRequest() {
    this.httpMethod = HttpMethod.GET;
    this.serviceUrl = "distancematrix";
    this.response = DistanceMatrixResponse.class;
  }
  
  public static DistanceMatrixRequest gen() {
    return new DistanceMatrixRequest();
  }
  
  public DistanceMatrixRequest addDestination(Location location) {
    this.destinations.add(location);
    return this;
  }
  
  public DistanceMatrixRequest addOrigin(Location location) {
    this.origins.add(location);
    return this;
  }
  
  public DistanceMatrixRequest arrivalTime(Date arrivalTime) {
    this.arrival_time = arrivalTime;
    return this;
  }
  
  public DistanceMatrixRequest departureTime(Date departureTime) {
    this.departure_time = departureTime;
    return this;
  }
  
  public DistanceMatrixRequest units(Unit units) {
    this.units = units;
    return this;
  }
  
  public DistanceMatrixRequest language(Language language) {
    this.language = language;
    return this;
  }
  
  public DistanceMatrixRequest mode(TravelMode mode) {
    this.mode = mode;
    return this;
  }

}
