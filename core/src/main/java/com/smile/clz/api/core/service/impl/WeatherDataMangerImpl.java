package com.smile.clz.api.core.service.impl;

import static java.text.MessageFormat.format;

import com.smile.clz.api.beans.WeatherData;
import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.WeatherAPI;
import com.smile.clz.api.core.service.WeatherDataManger;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    if(weatherData == null) {
      String error = "WeatherData object not be null";
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
    }

    if (weatherData.getWeather() == null) {
      String error = "Weather data not be null";
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
    }

    if (CollectionUtils.isEmpty(weatherData.getWeather())) {
      String error = "Weather data not be null";
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
    }

    if (weatherData.getWeather().get(0) == null) {
      String error = "Weather data not be null";
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
    }

    if (StringUtils.isBlank(weatherData.getWeather().get(0).getMain())) {
      String error = "Weather statement should not be null";
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
    }


    LOGGER.info("Found http response from where API [{}]", weatherData);
    return weatherData;
  }


}
