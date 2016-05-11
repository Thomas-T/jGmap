package com.allocab.jGmap.common.serializer;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DepartureTimeSerializer extends JsonSerializer<Date> {
  @Override
  public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
          throws IOException, JsonGenerationException {
    
    if(value == null) {
      return;
    }
    
    if(value.before(new Date())) {
      jgen.writeString("now");
    }
    else {
      jgen.writeNumber(value.getTime());
    }
      
  }
}
