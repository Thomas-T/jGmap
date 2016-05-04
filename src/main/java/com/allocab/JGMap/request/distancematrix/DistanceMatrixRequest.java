package com.allocab.JGMap.request.distancematrix;

import java.util.Date;

import com.allocab.JGMap.common.Avoid;
import com.allocab.JGMap.common.DepartureTimeSerializer;
import com.allocab.JGMap.common.Language;
import com.allocab.JGMap.common.TravelMode;
import com.allocab.JGMap.common.Unit;
import com.allocab.JGMap.request.AbstractRequest;
import com.allocab.JGMap.response.distancematrix.DistanceMatrixResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class DistanceMatrixRequest extends AbstractRequest<DistanceMatrixResponse>{
  
  public TravelMode mode = TravelMode.DRIVING;
  public Language language;
  public Avoid avoid;
  public Unit units;
  @JsonSerialize(using = DepartureTimeSerializer.class) 
  public Date departure_time;
  public Date arrival_time;
  
  public static DistanceMatrixRequest gen() {
    return new DistanceMatrixRequest();
  }

  @Override
  public String toParameters() {
    // TODO Auto-generated method stub
    return null;
  }

}
