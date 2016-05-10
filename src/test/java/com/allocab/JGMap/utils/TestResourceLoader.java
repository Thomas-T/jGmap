package com.allocab.jGmap.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.allocab.jGmap.ResourceLoader;

public class TestResourceLoader {

  @Test
  public void testFileLoadingAsString() {
    String file = ResourceLoader.loadString(TestResourceLoader.class, "/data/direction.json");
    assertNotNull(file);
  }
  
}
