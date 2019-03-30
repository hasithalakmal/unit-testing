package com.smile.clz.api.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.clz.api.beans.WeatherData;
import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.WeatherAPI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WeatherAPIImpl implements WeatherAPI {

  private static final Logger LOGGER = LoggerFactory.getLogger(WeatherAPIImpl.class);

  @Override
  public WeatherData executeGetWeatherData(String url) throws ClassCastException {
    HttpClient client = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(url);

    // add request header
    request.addHeader("Accept", "application/json");
    try {
      HttpResponse response = client.execute(request);
      LOGGER.info("Found http response from where API [{}]", response);
      WeatherData weatherData = validateWeatherResponse(response);
      return weatherData;
    } catch (Exception ex) {
      String error = "Failed calling weather API";
      LOGGER.error(error, ex);
      throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
    }
  }

  private WeatherData validateWeatherResponse(HttpResponse response) {
    try {
      if (response == null) {
        String error = "Weather API response should not be null";
        LOGGER.error(error);
        throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
      }

      if (response.getStatusLine() == null) {
        String error = "Weather API response status should not be null";
        LOGGER.error(error);
        throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
      }

      if (response.getStatusLine().getStatusCode() != 200) {
        String error = "Weather API response status should not be null";
        LOGGER.error(error);
        throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
      }

      if (response.getEntity() == null || response.getEntity().getContent() == null) {
        String error = "Weather API response entity should not be null";
        LOGGER.error(error);
        throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
      }

      if (response.getEntity().getContent() == null) {
        String error = "Weather API response entity should not be null";
        LOGGER.error(error);
        throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
      }

      ObjectMapper mapper = new ObjectMapper();
      WeatherData weatherData = mapper.readValue(response.getEntity().getContent(), WeatherData.class);
      LOGGER.info("Found weather data response [{}]", weatherData);

      if (weatherData == null) {
        String error = "Weather should not be null";
        LOGGER.error(error);
        throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
      }
      return weatherData;
    } catch (Exception ex) {
      String error = "Error occured in validating weather data";
      LOGGER.error(error, ex);
      throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.WEATHER_API_ERROR, error);
    }
  }
}
