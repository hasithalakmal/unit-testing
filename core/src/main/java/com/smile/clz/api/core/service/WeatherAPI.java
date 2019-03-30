package com.smile.clz.api.core.service;

import com.smile.clz.api.beans.WeatherData;

public interface WeatherAPI {

  WeatherData executeGetWeatherData(String url) throws ClassCastException;

}
