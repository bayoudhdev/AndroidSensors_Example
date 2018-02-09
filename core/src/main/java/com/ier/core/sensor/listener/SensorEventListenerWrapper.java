package com.ier.core.sensor.listener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.ier.core.sensor.event.CoreSensorEvent;

import io.reactivex.FlowableEmitter;

/**
 * Created by mohamed.bayoudh on 01/02/2018.
 */
public class SensorEventListenerWrapper {

    private FlowableEmitter<CoreSensorEvent> emitter;

    public FlowableEmitter<CoreSensorEvent> getEmitter() {
        return emitter;
    }

    public void setEmitter(FlowableEmitter<CoreSensorEvent> emitter) {
        this.emitter = emitter;
    }

    public SensorEventListener create() {
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                CoreSensorEvent event = new CoreSensorEvent(sensorEvent);
                if (getEmitter() != null) {
                    getEmitter().onNext(event);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                CoreSensorEvent event = new CoreSensorEvent(sensor, accuracy);
                if (getEmitter() != null) {
                    getEmitter().onNext(event);
                }
            }
        };
    }
}
