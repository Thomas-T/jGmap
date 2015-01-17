package com.allocab.JGMap.response.direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.allocab.JGMap.common.TravelMode;


public class Step {
  
  private String html_instructions;
  private Mesure distance;
  private Mesure duration;
  private Point start_location;
  private Point end_location;
  private Polyline polyline;
  private Step[] steps;
  private TravelMode travel_mode;
  private String maneuver;
  
  private Segment[] segments;
  
  public transient long secondElapsedInStep; 
  
  public Segment getSegmentAtDuration(long secondElapsedInStep) {
    //System.out.println("secondElapsedInStep : "+secondElapsedInStep);
    long secondsAtSegmentEnd = 0;
    for(Segment segment : segments) {
      secondsAtSegmentEnd += segment.getDuration().getValue();
      if(secondsAtSegmentEnd > secondElapsedInStep) {
        segment.secondElapsedInSegment = secondsAtSegmentEnd - secondElapsedInStep;
        return segment;
      }
    }    
    //System.out.println("returning last seg");
    return segments[segments.length-1];
  }
  
  public void buildSegments() {
    long totalDistance = distance.getValue();
    
    //System.out.println("step distance : "+totalDistance);
    
    List<Point> points = polyline.decode();
    
    //System.out.println("nb points in poly : "+points.size());
    
    List<Segment> segments = new ArrayList<Segment>();
    
    long segmentsDistance = 0;
    
    int i = 0;
    Segment segment;
    while(i < points.size() - 1) {      
      segment = new Segment(points.get(i), points.get(i+1));  
      segments.add(segment);
      
      segmentsDistance += segment.getDistance().getValue();
      
      i++;
    }
    
    //System.out.println("segments created : "+segments.size());
    //System.out.println("segments total distance created : "+segmentsDistance);
    
    double secondsByMeters = (double)duration.getValue() / (double)segmentsDistance;
    //System.out.println("it take "+secondsByMeters+" secs to make 1 m");
    
    for(Segment s : segments) {
      Mesure duration = new Mesure();
      double d = secondsByMeters * s.getDistance().getValue();
      duration.setValue(Math.round(d));
      s.setDuration(duration);
    }
    
    this.segments = segments.toArray(new Segment[segments.size()]);
    
  }
  
  public String getHtml_instructions() {
    return html_instructions;
  }
  public void setHtml_instructions(String html_instructions) {
    this.html_instructions = html_instructions;
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
  public Polyline getPolyline() {
    return polyline;
  }
  public void setPolyline(Polyline polyline) {
    this.polyline = polyline;
  }
  public Step[] getSteps() {
    return steps;
  }
  public void setSteps(Step[] steps) {
    this.steps = steps;
  }
  public TravelMode getTravel_mode() {
    return travel_mode;
  }
  public void setTravel_mode(TravelMode travel_mode) {
    this.travel_mode = travel_mode;
  }
  public String getManeuver() {
    return maneuver;
  }
  public void setManeuver(String maneuver) {
    this.maneuver = maneuver;
  }
  public Segment[] getSegments() {
    return segments;
  }
  public void setSegments(Segment[] segments) {
    this.segments = segments;
  }

}
