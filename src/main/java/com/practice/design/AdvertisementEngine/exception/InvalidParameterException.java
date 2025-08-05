package com.practice.design.AdvertisementEngine.exception;

public class InvalidParameterException extends AdvertisementEngineException {
  public InvalidParameterException(String parameterName, String reason) {
    super(String.format("Invalid parameter '%s': %s", parameterName, reason));
  }
}
