package com.allocab.jGmap;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.io.CharStreams;

public class ResourceLoader {

  public static String loadString(Class<?> clz, String path) {
    InputStream in = clz.getResourceAsStream(path);
    try {
      return CharStreams.toString(new InputStreamReader(in, "UTF-8"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
}
