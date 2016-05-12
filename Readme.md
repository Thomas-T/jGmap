#Google Maps WebServices for JAVA

Allow you to consume Google Maps WebServices in your JAVA project. This library is compatible with Google Appengine (no use of HTTPOK or async libs).


Master | [![Build Status](https://travis-ci.org/Thomas-T/jGmap.svg?branch=master)](https://travis-ci.org/Thomas-T/jGmap) | [![Coverage Status](https://coveralls.io/repos/github/Thomas-T/jGmap/badge.svg?branch=master)](https://coveralls.io/github/Thomas-T/jGmap?branch=master)
----|----|----
*Develop* | [![Build Status](https://travis-ci.org/Thomas-T/jGmap.svg?branch=master)](https://travis-ci.org/Thomas-T/jGmap) | [![Coverage Status](https://coveralls.io/repos/github/Thomas-T/jGmap/badge.svg?branch=develop)](https://coveralls.io/github/Thomas-T/jGmap?branch=develop)

## Supported Services
Currently only 2 services are supported

* [Direction](https://developers.google.com/maps/documentation/directions/?hl=en)
* [DistanceMatrix](https://developers.google.com/maps/documentation/distance-matrix/?hl=en)

## Using Direction Service
```java
DirectionResponse dr = DirectionService.gen()
	.origin("19 rue de la viabert, lyon, France")
	.destination("102 avenue saxe, lyon, France")
	.call();
```
Generate a `DirectionService`, add the `origin`address, the `destination` address, then use the method `call()` to get a `DirectionResponse` object.

The `DirectionResponse` object has the same definition of the one defined by GoogleMaps. [See the GoogleMaps definition](https://developers.google.com/maps/documentation/directions/intro?hl=en#DirectionsResponses)

## Using Distance Matrix
```java
DistanceMatrixResponse response = DistanceMatrixService.gen()
	.addOrigin("19 rue de la viabert, 69006 Lyon")
	.addOrigin("102 avenue maréchal de saxe 69003 Lyon")
	.addDestination("102 avenue maréchal de saxe 69003 Lyon")
 	.addDestination("19 rue de la viabert, 69006 Lyon")
	.call();
```        
Generate a `DistanceMatrixService`, add your `origin` addresses and your `destination` addresses. Using `call()`return your a `DistanceMatrixResponse` object that match the [Google Maps definition](https://developers.google.com/maps/documentation/distance-matrix/intro?hl=en#DistanceMatrixResponses)

## Google Maps Premier
You can set up the lib with your Google Maps Premier credentials.


```java
AbstractRequest.setApiClientId("my-client-id");
AbstractRequest.setApiCryptoKey("my-api-key");
```

This will sign all of your requests and let you access special features like traffic estimation.
