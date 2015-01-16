package com.allocab.JGMap.response.direction;

import java.util.List;

public class Polyline {
  private String points;

  public String getPoints() {
    return points;
  }

  public void setPoints(String points) {
    this.points = points;
  }
  
  public List<Point> decode() {
    return PolylineDecoder.decode(points);
  }
}
