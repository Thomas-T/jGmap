package com.allocab.jGmap.response.direction;

import java.io.Serializable;
import java.util.Date;

public class Time  implements Serializable{
  private Date value;
  private String text;
  private String time_zone;
  public Date getValue() {
    return value;
  }
  public void setValue(Date value) {
    this.value = value;
  }
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public String getTime_zone() {
    return time_zone;
  }
  public void setTime_zone(String time_zone) {
    this.time_zone = time_zone;
  }
}
