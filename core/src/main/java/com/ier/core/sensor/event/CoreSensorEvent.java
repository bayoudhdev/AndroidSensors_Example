package com.ier.core.sensor.event;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * Created by mohamed.bayoudh on 01/02/2018.
 */
public class CoreSensorEvent {
    private SensorEvent sensorEvent;
    private Sensor sensor;
    private int accuracy = -1;

    public CoreSensorEvent(SensorEvent sensorEvent) {
        this.sensorEvent = sensorEvent;
    }

    public CoreSensorEvent(Sensor sensor, int accuracy) {
        this.sensor = sensor;
        this.accuracy = accuracy;
    }

    public SensorEvent getSensorEvent() {
        return sensorEvent;
    }

    public boolean isSensorChanged() {
        return sensorEvent != null;
    }

    public boolean isAccuracyChanged() {
        return sensor != null && accuracy != -1;
    }
}
