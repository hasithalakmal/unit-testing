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
public class Location implements Serializable {

  private BigDecimal lon;
  private BigDecimal lat;

  public BigDecimal getLon() {
    return lon;
  }

  public void setLon(BigDecimal lon) {
    this.lon = lon;
  }

  public BigDecimal getLat() {
    return lat;
  }

  public void setLat(BigDecimal lat) {
    this.lat = lat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Location location = (Location) o;

    return new EqualsBuilder()
        .append(lon, location.lon)
        .append(lat, location.lat)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(lon)
        .append(lat)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("lon", lon)
        .append("lat", lat)
        .toString();
  }
}
