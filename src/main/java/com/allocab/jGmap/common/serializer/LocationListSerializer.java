package com.allocab.jGmap.common.serializer;

import java.io.IOException;

import com.allocab.jGmap.common.LocationList;
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