package com.allocab.jGmap.common;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Mesure  implements Serializable{
  private long value;
  private String text;
  public long getValue() {
    return value;
  }
  public void setValue(long value) {
    this.value = value;
  }
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public void incValue(long value) {
    this.value += value;
  }
}
