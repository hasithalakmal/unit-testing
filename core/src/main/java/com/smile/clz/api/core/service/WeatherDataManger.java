package com.smile.clz.api.core.service;

import com.smile.clz.api.beans.WeatherData;

public interface WeatherDataManger {

  WeatherData getWeatherDataByCityName(String cityName);
}
