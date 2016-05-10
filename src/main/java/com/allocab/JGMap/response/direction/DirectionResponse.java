package com.allocab.JGMap.response.direction;


import com.allocab.JGMap.response.AbstractResponse;

@SuppressWarnings("serial")
public class DirectionResponse extends AbstractResponse {
  
  public enum Comparison {
    DURATION,
    DURATION_IN_TRAFFIC,
    DISTANCE
  }
  
  public static DirectionResponse deserialize(String response) {
    DirectionResponse direction = AbstractResponse.deserialize(DirectionResponse.class, response);
    for(Route route : direction.routes) {
      route.calcDistance();
      route.calcDuration();
      route.calcDurationInTraffic();
    }
    return direction;
  }
  
  private Route[] routes;

  public Route[] getRoutes() {
    return routes;
  }

  public void setRoutes(Route[] routes) {
    this.routes = routes;
  }
  
  public Route getShortestRoute(Comparison comp) {
    if(this.routes == null) {
      return null;
    }
    if(this.routes.length == 1) {
      return this.routes[0]; 
    }
    
    Route shortest = null;
    for(Route route : routes) {
      switch(comp) {
        case DURATION:
          if(shortest == null || route.getDuration().getValue() <= shortest.getDuration().getValue()) {
            shortest = route;
          }
        break;
        case DURATION_IN_TRAFFIC:
          if(shortest == null || route.getDuration_in_traffic().getValue() <= shortest.getDuration_in_traffic().getValue()) {
            shortest = route;
          }
        break;
        case DISTANCE:
          if(shortest == null || route.getDistance().getValue() <= shortest.getDistance().getValue()) {
            shortest = route;
          }
        break;
      }      
    }   
    return shortest;
  }
  
  public Route getLongestRoute(Comparison comp) {
    if(this.routes == null) {
      return null;
    }
    if(this.routes.length == 1) {
      return this.routes[0]; 
    }
    
    Route shortest = null;
    for(Route route : routes) {
      switch(comp) {
        case DURATION:
          if(shortest == null || route.getDuration().getValue() >= shortest.getDuration().getValue()) {
            shortest = route;
          }
        break;
        case DURATION_IN_TRAFFIC:
          if(shortest == null || route.getDuration_in_traffic().getValue() >= shortest.getDuration_in_traffic().getValue()) {
            shortest = route;
          }
        break;
        case DISTANCE:
          if(shortest == null || route.getDistance().getValue() >= shortest.getDistance().getValue()) {
            shortest = route;
          }
        break;
      }      
    }   
    return shortest;
  }
  

}
