package com.allocab.JGMap.response.direction;

import java.util.Date;

import com.allocab.JGMap.response.direction.Direction.Comparison;

public class Route {
  private String summary;
  private Leg[] legs;
  private int[] waypoint_order;
  private Polyline overview_polyline;
  private Bound bounds;
  private String copyrights;
  private String[] warnings;
  
  private Mesure duration;
  private Mesure duration_in_traffic;
  private Mesure distance;
  
  public Point getPointAtForDepartureAt(Comparison comp, Date at, Date departureAt) {
    Segment segment = this.getSegmentAtForDepartureAt(comp, at, departureAt);
    return segment.getPositionInSegment(segment.secondElapsedInSegment);
  }

  public Segment getSegmentAtForDepartureAt(Comparison comp, Date at, Date departureAt) {
    Step step = this.getStepAtForDepartureAt(comp, at, departureAt);
    if(step.getSegments() == null) {
      step.buildSegments();
    }
    return step.getSegmentAtDuration(step.secondElapsedInStep);
  }  
  
  public Step getStepAtForDepartureAt(Comparison comp, Date at, Date departureAt) {
    Leg leg = this.getLegAtForDepartureAt(comp, at, departureAt);
    return leg.getStepAtDuration(leg.secondElapsedInLeg);
  }
  
  private Leg getLegAtForDepartureAt(Comparison comp, Date at, Date departureAt) {
    long elapsedSeconds = (at.getTime() - departureAt.getTime()) / 1000;
    long secondsAtLegEnd = 0;
    for(Leg leg : legs) {
      long secondsAtLegStart = secondsAtLegEnd;
      if(comp.equals(Comparison.DURATION_IN_TRAFFIC)) {
        secondsAtLegEnd += leg.getDuration_in_traffic().getValue();
      }
      else {
        secondsAtLegEnd += leg.getDuration().getValue();
      }      
      if(secondsAtLegEnd > elapsedSeconds) {
        leg.secondElapsedInLeg = elapsedSeconds - secondsAtLegStart;
        return leg;
      }
    }    
    return legs[legs.length-1];
  }
  
  public void calcDuration() {
    duration = new Mesure();    
    for(Leg leg : legs) {
      duration.incValue(leg.getDuration().getValue());      
    }    
  }
  public void calcDurationInTraffic() {
    duration_in_traffic = new Mesure();    
    for(Leg leg : legs) {
      if(leg.getDuration_in_traffic() == null) {
        return;
      }
      duration_in_traffic.incValue(leg.getDuration_in_traffic().getValue());      
    }    
  }
  public void calcDistance() {
    distance = new Mesure();
    for(Leg leg : legs) {
      distance.incValue(leg.getDistance().getValue());      
    }    
  }
  
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
  public Leg[] getLegs() {
    return legs;
  }
  public void setLegs(Leg[] legs) {
    this.legs = legs;
  }
  public int[] getWaypoint_order() {
    return waypoint_order;
  }
  public void setWaypoint_order(int[] waypoint_order) {
    this.waypoint_order = waypoint_order;
  }
  public Polyline getOverview_polyline() {
    return overview_polyline;
  }
  public void setOverview_polyline(Polyline overview_polyline) {
    this.overview_polyline = overview_polyline;
  }
  public Bound getBounds() {
    return bounds;
  }
  public void setBounds(Bound bounds) {
    this.bounds = bounds;
  }
  public String getCopyrights() {
    return copyrights;
  }
  public void setCopyrights(String copyrights) {
    this.copyrights = copyrights;
  }
  public String[] getWarnings() {
    return warnings;
  }
  public void setWarnings(String[] warnings) {
    this.warnings = warnings;
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
  public Mesure getDuration_in_traffic() {
    return duration_in_traffic;
  }
  public void setDuration_in_traffic(Mesure duration_in_traffic) {
    this.duration_in_traffic = duration_in_traffic;
  }

}
