package com.allocab.JGMap.distancematrix;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allocab.JGMap.response.AbstractResponse;
import com.allocab.JGMap.response.distancematrix.DistanceMatrixResponse;
import com.allocab.JGMap.service.DistanceMatrixService;

public class DistanceMatrixServiceTest {

  @Test
  public void testBasicServiceUsage() {
    DistanceMatrixResponse response = DistanceMatrixService.gen().addOrigin("19 rue de la viabert, 69006 Lyon").addDestination("102 avenue maréchal de saxe 69003 Lyon").call();
    assertNotNull(response);
    assertEquals(AbstractResponse.Status.OK, response.getStatus());
    
  }
  
  @Test
  public void testTwoPairsServiceUsage() {
    DistanceMatrixResponse response = DistanceMatrixService.gen()
        .addOrigin("19 rue de la viabert, 69006 Lyon")
        .addOrigin("102 avenue maréchal de saxe 69003 Lyon")
        .addDestination("102 avenue maréchal de saxe 69003 Lyon")
        .addDestination("19 rue de la viabert, 69006 Lyon")
        .call();
    assertNotNull(response);
    assertEquals(AbstractResponse.Status.OK, response.getStatus());
    
  }
  
}
