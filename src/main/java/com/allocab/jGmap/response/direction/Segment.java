package com.allocab.jGmap.response.direction;

import java.io.Serializable;

import com.allocab.jGmap.common.Mesure;

public class Segment  implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Point start_location;
  private Point end_location;
  private Mesure duration;
  private Mesure distance;
  
  public transient long secondElapsedInSegment;
  
  protected Segment() {}
  
  public Segment(Point start_location, Point end_location) {
    this.start_location = start_location;
    this.end_location = end_location;
    calcDistance();
  }
  
  public Point getPositionInSegment(Long secondElapsedInSegment) {
    //System.out.println("seconds elapsed in segment : "+secondElapsedInSegment);
    
    double prog = 0;
    if(secondElapsedInSegment >= duration.getValue()) {
      prog = 1;
    }
    else {
      prog = 1-((double)secondElapsedInSegment / (double)duration.getValue());
    }

    
    //System.out.println("segment prog : "+prog+" "+secondElapsedInSegment+" "+duration.getValue());
    
    double dLat = (end_location.getLat() - start_location.getLat()) * prog;
    double dLng = (end_location.getLng() - start_location.getLng()) * prog;
    
    return new Point(start_location.getLat()+dLat, start_location.getLng()+dLng);
  }
  
  public void calcDistance() {
    distance = new Mesure();
    distance.setValue(start_location.distanceInMeters(end_location));
  }
  
  public Point getStart_location() {
    return start_location;
  }
  public void setStart_location(Point start_location) {
    this.start_location = start_location;
  }
  public Point getEnd_location() {
    return end_location;
  }
  public void setEnd_location(Point end_location) {
    this.end_location = end_location;
  }
  public Mesure getDuration() {
    return duration;
  }
  public void setDuration(Mesure duration) {
    this.duration = duration;
  }
  public Mesure getDistance() {
    return distance;
  }
  public void setDistance(Mesure distance) {
    this.distance = distance;
  }
}
