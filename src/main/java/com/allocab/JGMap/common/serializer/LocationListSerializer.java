package com.allocab.JGMap.common.serializer;

import java.io.IOException;
import java.util.Date;

import com.allocab.JGMap.request.distancematrix.LocationList;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocationListSerializer extends JsonSerializer<LocationList> {
  @Override
  public void serialize(LocationList value, JsonGenerator jgen, SerializerProvider provider)
          throws IOException, JsonGenerationException {
    
    if(value == null) {
      return;
    }    
    jgen.writeString(value.toParam());      
  }
}