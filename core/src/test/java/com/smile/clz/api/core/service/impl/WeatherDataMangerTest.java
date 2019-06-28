package com.smile.clz.api.core.service.impl;

import static org.testng.Assert.fail;

import com.smile.clz.api.beans.WeatherData;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.BaseManager;
import com.smile.clz.api.core.service.WeatherDataManger;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeatherDataMangerTest extends BaseManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestNgExecutionFlowDemoTest.class);

  @Autowired
  private WeatherDataManger weatherDataManger;

  @Test
  public void getWeatherDataByCityWithoutMocking() {
    //This is the sample API - https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22

    try {
      WeatherData weatherData = weatherDataManger.getWeatherDataByCityName("London,uk");
      Assert.assertNotNull(weatherData);
      Assert.assertNotNull(weatherData.getCoord());
      Assert.assertEquals(weatherData.getCoord().getLat(), new BigDecimal("51.51"));
      Assert.assertEquals(weatherData.getCoord().getLon(), new BigDecimal("-0.13"));
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

}
