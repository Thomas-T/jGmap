package com.allocab.jGmap.distancematrix;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import org.junit.Test;

import com.allocab.jGmap.request.AbstractRequest;
import com.allocab.jGmap.response.AbstractResponse;
import com.allocab.jGmap.response.distancematrix.DistanceMatrixResponse;
import com.allocab.jGmap.response.distancematrix.DistanceMatrixResponse.Element;
import com.allocab.jGmap.response.distancematrix.DistanceMatrixResponse.Row;
import com.allocab.jGmap.service.DistanceMatrixService;

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
  
  @Test
  public void testTwoPairsServiceUsageWithPremierKeyAndTraffic() {
	  
	//System.out.println("GMAP_CLIENT_ID:"+System.getenv("GMAP_CLIENT_ID"));
	//System.out.println("GMAP_CRYPTO_KEY:"+System.getenv("GMAP_CRYPTO_KEY"));
	AbstractRequest.setApiClientId(System.getenv("GMAP_CLIENT_ID"));
	AbstractRequest.setApiCryptoKey(System.getenv("GMAP_CRYPTO_KEY"));
	  
    DistanceMatrixResponse response = DistanceMatrixService.gen()
    	.departureTime(new Date())	
        .addOrigin("19 rue de la viabert, 69006 Lyon")
        .addOrigin("102 avenue maréchal de saxe 69003 Lyon")
        .addDestination("102 avenue maréchal de saxe 69003 Lyon")
        .addDestination("19 rue de la viabert, 69006 Lyon")
        .call();
    assertNotNull(response);
    assertEquals(AbstractResponse.Status.OK, response.getStatus());
    
    Row row = response.rows.get(0);
    Element element = row.elements.get(0);
    assertNotNull(element.duration_in_traffic);    

	AbstractRequest.setApiClientId(null);
	AbstractRequest.setApiCryptoKey(null);    
  }
  
}
