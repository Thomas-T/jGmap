package com.allocab.jGmap.common;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class LocationList extends ArrayList<Location> implements Serializable, Parameterizable {

  @Override
  public String toParam() {
    String param = "";
    for(Location location : this) {
      param += location.toParam();
      if(this.indexOf(location) < this.size()-1) {
        param += "|";
      }
    }
    return param;
  }

}