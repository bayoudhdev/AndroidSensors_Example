package com.ier.core.sensor.helpers;

import android.hardware.SensorEvent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.ier.core.sensor.SchedulersFacade;
import com.ier.core.sensor.event.CoreSensorFilter;
import com.ier.core.sensor.exception.SensorNotFoundException;
import com.ier.core.sensor.listener.CoreObserverResult;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by mohamed.bayoudh on 08/02/2018.
 */
public class SensorHelper {
    private final SchedulersFacade mSchedulersFacade;
    private CoreSensorsHelper mCoreSensors;
    public static final float DEFAULT_ACCELERATION_CHOC = 2f;
    public static final int DEFAULT_ACCELERATION_SHAKE = 800;

    @Inject
    public SensorHelper(SchedulersFacade schedulersFacade, CoreSensorsHelper sensors) {
        this.mSchedulersFacade = schedulersFacade;
        this.mCoreSensors = sensors;
    }

    /**
     * Method used to get light Sensor and return true when nothing to show
     *
     * @param sensorType
     * @param resultObserver
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public Disposable getLightSensors(int sensorType, CoreObserverResult<Float> resultObserver) {
        return mCoreSensors.observeSensor(sensorType)
                .subscribeOn(mSchedulersFacade.computation())
                .observeOn(mSchedulersFacade.ui())
                .filter(CoreSensorFilter.filterSensorChanged())
                .subscribe(coreSensorEvent -> {
                    SensorEvent event = coreSensorEvent.getSensorEvent();
                    float x = event.values[0];
                    resultObserver.onNext(x);
                }, throwable -> {
                    if (throwable instanceof SensorNotFoundException) {
                        SensorNotFoundException sensorNotFoundException = (SensorNotFoundException) throwable;
                        resultObserver.onError(sensorNotFoundException);
                    }
                });
    }

    /**
     * Method used to get Object gravity above device
     *
     * @param sensorType
     * @param resultObserver
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public Disposable getProximitySensors(int sensorType, CoreObserverResult<Boolean> resultObserver) {
        return mCoreSensors.observeSensor(sensorType)
                .subscribeOn(mSchedulersFacade.computation())
                .observeOn(mSchedulersFacade.ui())
                .filter(CoreSensorFilter.filterSensorChanged())
                .subscribe(coreSensorEvent -> {
                    SensorEvent event = coreSensorEvent.getSensorEvent();
                    float x = event.values[0];
                    resultObserver.onNext(x == 0 ? true : false);
                }, throwable -> {
                    if (throwable instanceof SensorNotFoundException) {
                        SensorNotFoundException sensorNotFoundException = (SensorNotFoundException) throwable;
                        resultObserver.onError(sensorNotFoundException);
                    }
                });
    }

    /**
     * Method used to get choc sensors and return boolean when device have choc vibration > 800
     *
     * @param sensorType
     * @param resultObserver
     * @return
     */
    public Disposable getChocSensors(int sensorType, CoreObserverResult<Boolean> resultObserver) {
        return mCoreSensors.observeSensor(sensorType)
                .subscribeOn(mSchedulersFacade.computation())
                .observeOn(mSchedulersFacade.ui())
                .filter(CoreSensorFilter.filterSensorChanged())
                .subscribe(coreSensorEvent -> {
                    SensorEvent event = coreSensorEvent.getSensorEvent();
                    float ax = event.values[0];
                    float ay = event.values[1];
                    float az = event.values[2];
                    float linear = ax * ax + ay * ay;
                    double magnitudeSquared = az * az;
                    if (magnitudeSquared <= linear && linear <= 4) {
                        resultObserver.onNext(magnitudeSquared < DEFAULT_ACCELERATION_CHOC ? true : false);
                    }
                }, throwable -> {
                    if (throwable instanceof SensorNotFoundException) {
                        SensorNotFoundException sensorNotFoundException = (SensorNotFoundException) throwable;
                        resultObserver.onError(sensorNotFoundException);
                    }
                });
    }

    /**
     * Method used to return shake value from ax gravity
     *
     * @param sensorType
     * @param resultObserver
     * @return
     */
    public Disposable getShakeSensors(int sensorType, CoreObserverResult<Boolean> resultObserver) {
        return mCoreSensors.observeSensor(sensorType)
                .subscribeOn(mSchedulersFacade.computation())
                .observeOn(mSchedulersFacade.ui())
                .filter(CoreSensorFilter.filterSensorChanged())
                .subscribe(coreSensorEvent -> {
                    SensorEvent event = coreSensorEvent.getSensorEvent();
                    float ax = event.values[0];
                    float ay = event.values[1];
                    float az = event.values[2];
                    double magnitudeSquared = ax * ax;
                    float linear = az * az + ay * ay;
                    if (magnitudeSquared >= linear) {
                        resultObserver.onNext(magnitudeSquared > DEFAULT_ACCELERATION_SHAKE ? true : false);
                    }
                }, throwable -> {
                    if (throwable instanceof SensorNotFoundException) {
                        SensorNotFoundException sensorNotFoundException = (SensorNotFoundException) throwable;
                        resultObserver.onError(sensorNotFoundException);
                    }
                });
    }
}
