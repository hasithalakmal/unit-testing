package com.smile.clz.api.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class WeatherData {
  private Location coord;
  private Weather weather;
  private MainData main;

  public Location getCoord() {
    return coord;
  }

  public void setCoord(Location coord) {
    this.coord = coord;
  }

  public Weather getWeather() {
    return weather;
  }

  public void setWeather(Weather weather) {
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
