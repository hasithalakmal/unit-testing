package com.smile.clz.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(
    value = JsonInclude.Include.NON_NULL
)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class WeatherData implements Serializable {

  private Location coord;
  private List<Weather> weather;
  private MainData main;

  public Location getCoord() {
    return coord;
  }

  public void setCoord(Location coord) {
    this.coord = coord;
  }

  public List<Weather> getWeather() {
    return weather;
  }

  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }

  public MainData getMain() {
    return main;
  }

  public void setMain(MainData main) {
    this.main = main;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    WeatherData that = (WeatherData) o;

    return new EqualsBuilder()
        .append(coord, that.coord)
        .append(weather, that.weather)
        .append(main, that.main)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(coord)
        .append(weather)
        .append(main)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("coord", coord)
        .append("weather", weather)
        .append("main", main)
        .toString();
  }
}
