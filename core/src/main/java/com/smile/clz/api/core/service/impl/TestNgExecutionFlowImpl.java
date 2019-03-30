package com.smile.clz.api.core.service.impl;

import com.smile.clz.api.core.service.TestNgExecutionFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestNgExecutionFlowImpl implements TestNgExecutionFlow {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestNgExecutionFlowImpl.class);

  @Override
  public void myGreeting() {
    LOGGER.info("Hello!!!");
  }
}
