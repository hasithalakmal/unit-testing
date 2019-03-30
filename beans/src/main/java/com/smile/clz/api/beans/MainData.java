package com.smile.clz.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(
    value = JsonInclude.Include.NON_NULL
)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class MainData implements Serializable {

  private BigDecimal temp;
  private BigDecimal pressure;
  private BigDecimal humidity;

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
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(temp)
        .append(pressure)
        .append(humidity)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("temp", temp)
        .append("pressure", pressure)
        .append("humidity", humidity)
        .toString();
  }
}
