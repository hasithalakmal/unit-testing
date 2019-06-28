package com.smile.clz.api.core.service.impl;

import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.TddDemo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TddDemoImpl implements TddDemo {

  @Override
  public double myDivide(double a, double b) {
    if(b==1) {
      throw new ClassApiException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETERS, "Second number should not be zero");
    }
    return a/b;
  }
}
