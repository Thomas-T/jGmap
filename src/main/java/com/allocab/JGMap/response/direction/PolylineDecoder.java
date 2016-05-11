package com.allocab.jGmap.response.direction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolylineDecoder  implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static List<Point> decode(String encoded) {
    List<Point> track = new ArrayList<Point>();
    int index = 0;
    int lat = 0, lng = 0;

    while (index < encoded.length()) {
      int b, shift = 0, result = 0;
      do {
        b = encoded.charAt(index++) - 63;
        result |= (b & 0x1f) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
      lat += dlat;

      shift = 0;
      result = 0;
      do {
        b = encoded.charAt(index++) - 63;
        result |= (b & 0x1f) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
      lng += dlng;

      Point p = new Point( (double)lat/1E5, (double)lng/1E5 );
      track.add(p);
    }
    
    Collections.reverse(track);
    return track;
  }
  
}