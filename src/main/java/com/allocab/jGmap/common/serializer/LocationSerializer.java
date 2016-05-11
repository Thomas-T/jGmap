package com.allocab.jGmap.common.serializer;

import java.io.IOException;

import com.allocab.jGmap.common.Location;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocationSerializer extends JsonSerializer<Location> {


  @Override
  public void serialize(Location value, JsonGenerator jgen, SerializerProvider provider)
          throws IOException, JsonGenerationException {
    jgen.writeString(value.toParam());
      
  }

}