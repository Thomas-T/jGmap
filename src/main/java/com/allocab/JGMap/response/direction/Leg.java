package com.allocab.jGmap.response.direction;

import java.io.Serializable;

import com.allocab.jGmap.common.Mesure;

public class Leg  implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Step[] steps;
  private Mesure distance;
  private Mesure duration;
  private Mesure duration_in_traffic;
  private Time departure_time;
  private Time arrival_time;
  private Point start_location;
  private Point end_location;
  private String start_address;
  private String end_address;
  private ViaWaypoint[] via_waypoint;
  
  public transient long secondElapsedInLeg; 
  
  public Step getStepAtDuration(long secondElapsedInLeg) {
    System.out.println("seconds elapsed in leg : "+secondElapsedInLeg);
    long secondsAtStepEnd = 0;
    for(Step step : steps) {
      secondsAtStepEnd += step.getDuration().getValue();
      if(secondsAtStepEnd > secondElapsedInLeg) {        
        step.secondElapsedInStep = secondsAtStepEnd - secondElapsedInLeg;
        //System.out.println("step.secondElapsedInStep : "+step.secondElapsedInStep);
        return step;
      }
    }    
    System.out.println("Returning last step");
    Step step = steps[steps.length-1];
    step.secondElapsedInStep = secondsAtStepEnd;
    return step;
  }
  
  public Step[] getSteps() {
    return steps;
  }
  public void setSteps(Step[] steps) {
    this.steps = steps;
  }
  public Mesure getDistance() {
    return distance;
  }
  public void setDistance(Mesure distance) {
    this.distance = distance;
  }
  public Mesure getDuration() {
    return duration;
  }
  public void setDuration(Mesure duration) {
    this.duration = duration;
  }
  public Mesure getDuration_in_traffic() {
    return duration_in_traffic;
  }
  public void setDuration_in_traffic(Mesure duration_in_traffic) {
    this.duration_in_traffic = duration_in_traffic;
  }
  public Time getDeparture_time() {
    return departure_time;
  }
  public void setDeparture_time(Time departure_time) {
    this.departure_time = departure_time;
  }
  public Time getArrival_time() {
    return arrival_time;
  }
  public void setArrival_time(Time arrival_time) {
    this.arrival_time = arrival_time;
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
  public String getStart_address() {
    return start_address;
  }
  public void setStart_address(String start_address) {
    this.start_address = start_address;
  }
  public String getEnd_address() {
    return end_address;
  }
  public void setEnd_address(String end_address) {
    this.end_address = end_address;
  }
  public ViaWaypoint[] getVia_waypoint() {
    return via_waypoint;
  }
  public void setVia_waypoint(ViaWaypoint[] via_waypoint) {
    this.via_waypoint = via_waypoint;
  }
}
