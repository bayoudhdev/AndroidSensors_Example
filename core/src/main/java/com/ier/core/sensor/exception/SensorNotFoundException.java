package com.ier.core.sensor.exception;

/**
 * Created by mohamed.bayoudh on 01/02/2018.
 */
public class SensorNotFoundException extends Exception {
    public SensorNotFoundException(final String message) {
        super(message);
    }

    public SensorNotFoundException() {
        super();
    }
}
