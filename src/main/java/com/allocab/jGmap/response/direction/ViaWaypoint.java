package com.allocab.jGmap.response.direction;

import java.io.Serializable;

public class ViaWaypoint  implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Point location;
  private long step_index;
  private double step_interpolation;
  public Point getLocation() {
    return location;
  }
  public void setLocation(Point location) {
    this.location = location;
  }
  public long getStep_index() {
    return step_index;
  }
  public void setStep_index(long step_index) {
    this.step_index = step_index;
  }
  public double getStep_interpolation() {
    return step_interpolation;
  }
  public void setStep_interpolation(double step_interpolation) {
    this.step_interpolation = step_interpolation;
  }
}
