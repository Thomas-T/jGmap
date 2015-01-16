package com.allocab.JGMap.response.direction;

public class Point {
  private double lat;
  private double lng;
  
  @SuppressWarnings("unused")
  private Point() {}
  
  public Point(double lat, double lng) {
    this.lat = lat;
    this.lng = lng;
  }
  
  public double getLat() {
    return lat;
  }
  public void setLat(double lat) {
    this.lat = lat;
  }
  public double getLng() {
    return lng;
  }
  public void setLng(double lng) {
    this.lng = lng;
  }
  
  public int distanceInMeters(Point b) {
    double x1 = Math.toRadians(lat);
    double y1 = Math.toRadians(lng);

    double x2 = Math.toRadians(b.getLat());
    double y2 = Math.toRadians(b.getLng());

    double sec1 = Math.sin(x1) * Math.sin(x2);
    double dl = Math.abs(y1 - y2);
    double sec2 = Math.cos(x1) * Math.cos(x2);

    double centralAngle = Math.acos(sec1 + sec2 * Math.cos(dl));

    double distance = centralAngle * 6378000.1;

    return (int) distance;
  }
}
