package com.allocab.jGmap.response.direction;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bound  implements Serializable{
  
  private Point northeast;
  private Point northwest;
  private Point southeast;
  private Point southwest;
  public Point getNortheast() {
    return northeast;
  }
  public void setNortheast(Point northeast) {
    this.northeast = northeast;
  }
  public Point getNorthwest() {
    return northwest;
  }
  public void setNorthwest(Point northwest) {
    this.northwest = northwest;
  }
  public Point getSoutheast() {
    return southeast;
  }
  public void setSoutheast(Point southeast) {
    this.southeast = southeast;
  }
  public Point getSouthwest() {
    return southwest;
  }
  public void setSouthwest(Point southwest) {
    this.southwest = southwest;
  }

}
