package com.ier.core.sensor.helpers;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;

import com.ier.core.sensor.event.CoreSensorEvent;
import com.ier.core.sensor.exception.SensorNotFoundException;
import com.ier.core.sensor.listener.SensorEventListenerWrapper;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by mohamed.bayoudh on 01/02/2018.
 */
public final class CoreSensorsHelper {

    private SensorManager sensorManager;

    /**
     * Creates CoreSensorsHelper object
     *
     * @param context context
     */
    @Inject
    public CoreSensorsHelper(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * Gets lists of all sensors available on device
     *
     * @return list of sensors
     */
    public List<Sensor> getSensors() {
        return sensorManager.getSensorList(Sensor.TYPE_ALL);
    }

    /**
     * Checks if device has given sensor available
     *
     * @param sensorType from Sensor class available in Android SDK
     * @return boolean returns true if sensor is available
     */
    public boolean hasSensor(int sensorType) {
        return sensorManager.getDefaultSensor(sensorType) != null;
    }

    /**
     * Returns RxJava Observable, which allows to monitor hardware sensors
     * as a stream of CoreSensorEvent object.
     * Sampling period is set to SensorManager.SENSOR_DELAY_NORMAL.
     *
     * @param sensorType sensor type from Sensor class from Android SDK
     * @return RxJava Observable with CoreSensorEvent
     */
    public Flowable<CoreSensorEvent> observeSensor(int sensorType) {
        return observeSensor(sensorType, SensorManager.SENSOR_DELAY_NORMAL, null);
    }

    /**
     * Returns RxJava Observable, which allows to monitor hardware sensors
     * as a stream of CoreSensorEvent object with defined sampling period
     *
     * @param sensorType         sensor type from Sensor class from Android SDK
     * @param samplingPeriodInUs sampling period in microseconds,
     *                           you can use predefined values from SensorManager class with prefix SENSOR_DELAY
     * @return RxJava Observable with CoreSensorEvent
     */
    public Flowable<CoreSensorEvent> observeSensor(int sensorType, final int samplingPeriodInUs) {
        return observeSensor(sensorType, samplingPeriodInUs, null);
    }

    public Flowable<CoreSensorEvent> observeSensor(int sensorType, final int samplingPeriodInUs,
                                                   final Handler handler) {
        return observeSensor(sensorType, samplingPeriodInUs, handler, BackpressureStrategy.BUFFER);
    }

    /**
     * Returns RxJava Observable, which allows to monitor hardware sensors
     * as a stream of CoreSensorEvent object with defined sampling period
     *
     * @param sensorType         sensor type from Sensor class from Android SDK
     * @param samplingPeriodInUs sampling period in microseconds,
     * @param handler            the Handler the sensor events will be delivered to, use default if it is null
     *                           you can use predefined values from SensorManager class with prefix SENSOR_DELAY
     * @param strategy           BackpressureStrategy for RxJava 2 Flowable type
     * @return RxJava Observable with CoreSensorEvent
     */
    public Flowable<CoreSensorEvent> observeSensor(int sensorType, final int samplingPeriodInUs,
                                                   final Handler handler, final BackpressureStrategy strategy) {

        if (!hasSensor(sensorType)) {
            String format = "Sensor with id = %d is not available on this device";
            String message = String.format(Locale.getDefault(), format, sensorType);
            return Flowable.error(new SensorNotFoundException(message));
        }

        final Sensor sensor = sensorManager.getDefaultSensor(sensorType);
        final SensorEventListenerWrapper wrapper = new SensorEventListenerWrapper();
        final SensorEventListener listener = wrapper.create();

        return Flowable.create((FlowableOnSubscribe<CoreSensorEvent>) emitter -> {

            wrapper.setEmitter(emitter);

            if (handler == null) {
                sensorManager.registerListener(listener, sensor, samplingPeriodInUs);
            } else {
                sensorManager.registerListener(listener, sensor, samplingPeriodInUs, handler);
            }
        }, strategy).doOnCancel(() -> sensorManager.unregisterListener(listener));
    }
}
