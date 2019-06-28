package com.smile.clz.api.core.service.impl;

import static org.testng.Assert.fail;

import com.smile.clz.api.beans.Location;
import com.smile.clz.api.beans.Weather;
import com.smile.clz.api.beans.WeatherData;
import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.BaseManager;
import com.smile.clz.api.core.service.WeatherAPI;
import com.smile.clz.api.core.service.WeatherDataManger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import mockit.Capturing;
import mockit.Expectations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeatherDataMangerTest2 extends BaseManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestNgExecutionFlowDemoTest.class);

  @Autowired
  private WeatherDataManger weatherDataManger;

  @Capturing
  private WeatherAPI weatherAPI;

  @Test
  public void getWeatherDataByCityWithMocking() {
    //This is the sample API - https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22

    WeatherData weatherData1 = new WeatherData();
    Location location = new Location();
    location.setLat(new BigDecimal("10.00"));
    location.setLon(new BigDecimal("15.00"));
    weatherData1.setCoord(location);

    new Expectations() {{
      try {
        weatherAPI.executeGetWeatherData(anyString);
      } catch (Exception e) {
        fail("Mocking of categoryManager failed, will be failing test scenarios due to dependencies :" + e);
      }
      result = weatherData1;
    }};

    try {
      WeatherData weatherData = weatherDataManger.getWeatherDataByCityName("London,uk");
      Assert.assertNotNull(weatherData);
      Assert.assertNotNull(weatherData.getCoord());
      Assert.assertEquals(weatherData.getCoord().getLat(), new BigDecimal("10.00"));
      Assert.assertEquals(weatherData.getCoord().getLon(), new BigDecimal("15.00"));
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

  @Test
  public void test2() {
    //This is the sample API - https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22

    WeatherData weatherData1 = new WeatherData();
    Location location = new Location();
    location.setLat(new BigDecimal("10.00"));
    location.setLon(new BigDecimal("15.00"));
    weatherData1.setCoord(location);

    Weather weather = new Weather();
    weather.setMain("     ");
    List<Weather> listOfWeathers = new ArrayList<>();
    listOfWeathers.add(weather);
    weatherData1.setWeather(listOfWeathers);

    new Expectations() {{
      try {
        weatherAPI.executeGetWeatherData(anyString);
      } catch (Exception e) {
        fail("Mocking of categoryManager failed, will be failing test scenarios due to dependencies :" + e);
      }
      result = weatherData1;
    }};

    try {
      WeatherData weatherData = weatherDataManger.getWeatherDataByCityName("London,uk");
      fail("error scenario should fail");
    } catch (ClassApiException ex) {
      ClassApiException e = (ClassApiException) ex;
      Assert.assertEquals(e.getErrorCode(), ErrorCode.WEATHER_API_ERROR );
      Assert.assertEquals(e.getHttpStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
