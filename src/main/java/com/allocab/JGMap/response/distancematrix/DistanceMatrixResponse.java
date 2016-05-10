package com.allocab.jGmap.response.distancematrix;

import java.util.List;

import com.allocab.jGmap.common.Mesure;
import com.allocab.jGmap.response.AbstractResponse;

@SuppressWarnings("serial")
public class DistanceMatrixResponse extends AbstractResponse {
  public List<String> origin_addresses;
  public List<String> destination_addresses;
  public List<Row> rows;
  
  public static DistanceMatrixResponse deserialize(String response) {
    return AbstractResponse.deserialize(DistanceMatrixResponse.class, response);
  }
  
  public static class Row {
    public List<Element> elements;
  }
  
  public static class Element {
    public AbstractResponse.Status status;
    public Mesure duration;
    public Mesure duration_in_traffic;
    public Mesure distance;
  }
}
