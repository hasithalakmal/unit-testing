package com.smile.clz.api.beans;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MainData {

  private BigDecimal temp;
  private BigDecimal pressure;
  private BigDecimal humidity;
  private BigDecimal temp_min;
  private BigDecimal temp_max;

  public BigDecimal getTemp() {
    return temp;
  }

  public void setTemp(BigDecimal temp) {
    this.temp = temp;
  }

  public BigDecimal getPressure() {
    return pressure;
  }

  public void setPressure(BigDecimal pressure) {
    this.pressure = pressure;
  }

  public BigDecimal getHumidity() {
    return humidity;
  }

  public void setHumidity(BigDecimal humidity) {
    this.humidity = humidity;
  }

  public BigDecimal getTemp_min() {
    return temp_min;
  }

  public void setTemp_min(BigDecimal temp_min) {
    this.temp_min = temp_min;
  }

  public BigDecimal getTemp_max() {
    return temp_max;
  }

  public void setTemp_max(BigDecimal temp_max) {
    this.temp_max = temp_max;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MainData mainData = (MainData) o;

    return new EqualsBuilder()
        .append(temp, mainData.temp)
        .append(pressure, mainData.pressure)
        .append(humidity, mainData.humidity)
        .append(temp_min, mainData.temp_min)
        .append(temp_max, mainData.temp_max)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(temp)
        .append(pressure)
        .append(humidity)
        .append(temp_min)
        .append(temp_max)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("temp", temp)
        .append("pressure", pressure)
        .append("humidity", humidity)
        .append("temp_min", temp_min)
        .append("temp_max", temp_max)
        .toString();
  }
}
