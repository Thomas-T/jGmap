package com.allocab.JGMap.distancematrix;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.JGMap.ResourceLoader;
import com.allocab.JGMap.direction.TestDirectionResponse;
import com.allocab.JGMap.response.AbstractResponse;
import com.allocab.JGMap.response.direction.DirectionResponse;
import com.allocab.JGMap.response.distancematrix.DistanceMatrixResponse;
import com.allocab.JGMap.response.distancematrix.DistanceMatrixResponse.Element;

public class DistanceMatrixResponseTest {

  @Test
  public void testSimpleMatrixDeserialization() {
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/distance_matrix_simple.json");
    DistanceMatrixResponse response = DistanceMatrixResponse.deserialize(directionStr);     
    assertNotNull(response);
    assertEquals(AbstractResponse.Status.OK, response.getStatus());
    assertEquals(1, response.rows.size());
    assertEquals(1, response.rows.get(0).elements.size());
    Element element = response.rows.get(0).elements.get(0);
    assertEquals("1,8 miles",element.distance.getText());
    assertEquals(2977,element.distance.getValue());
    
    assertEquals("12 minutes",element.duration.getText());
    assertEquals(732,element.duration.getValue());

    assertEquals(AbstractResponse.Status.OK, element.status);
  }
  
  @Test
  public void testTwoPairsMatrixDeserialization() {
    String directionStr = ResourceLoader.loadString(TestDirectionResponse.class, "/data/distance_matrix_two_pairs.json");
    DistanceMatrixResponse response = DistanceMatrixResponse.deserialize(directionStr);     
    assertNotNull(response);
    assertEquals(AbstractResponse.Status.OK, response.getStatus());
    assertEquals(2, response.rows.size());
    assertEquals(2, response.rows.get(0).elements.size());
    Element element = response.rows.get(0).elements.get(0);
    assertEquals("2,3 km",element.distance.getText());
    assertEquals(2272,element.distance.getValue());
    
    assertEquals("10 minutes",element.duration.getText());
    assertEquals(606,element.duration.getValue());

    assertEquals(AbstractResponse.Status.OK, element.status);
  }
  
}
