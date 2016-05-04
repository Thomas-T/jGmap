package com.allocab.JGMap.response.distancematrix;

import java.util.List;

import com.allocab.JGMap.common.Mesure;
import com.allocab.JGMap.response.AbstractResponse;

public class DistanceMatrixResponse extends AbstractResponse {
  public List<String> origin_addresses;
  public List<String> destination_addresses;
  
  public static class Row {
    public List<Element> elements;
  }
  
  public static class Element {
    public AbstractResponse.Status status;
    public Mesure duration;
    public Mesure distance;
  }
}
