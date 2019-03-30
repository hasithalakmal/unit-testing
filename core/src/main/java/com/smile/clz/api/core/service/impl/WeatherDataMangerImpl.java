package com.smile.clz.api.core.service.impl;

import static java.text.MessageFormat.format;

import com.smile.clz.api.beans.WeatherData;
import com.smile.clz.api.core.service.WeatherAPI;
import com.smile.clz.api.core.service.WeatherDataManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataMangerImpl implements WeatherDataManger {

  private static final Logger LOGGER = LoggerFactory.getLogger(WeatherDataMangerImpl.class);

  private static final String HTTP_URL = "https://samples.openweathermap.org/data/2.5/weather?q={0},uk&appid=b6907d289e10d714a6e88b30761fae22";

  @Autowired
  private WeatherAPI weatherAPI;

  @Override
  public WeatherData getWeatherDataByCityName(String cityName) {
    LOGGER.info("ready to execute getWeatherDataByCityName with city name [{}]", cityName);
    String url = format(HTTP_URL, cityName);
    WeatherData weatherData = weatherAPI.executeGetWeatherData(url);
    LOGGER.info("Found http response from where API [{}]", weatherData);
    return weatherData;
  }


}
