# AndroidSensors_Example

AndroidSensors is a simple library, that acts as a wrapper around the Android Sensor's system, converting the stream of sensor data, into a Flowable that emits the same events, so that you can combine more sources of data for more complex operations, RxJava2 compatible, Dagger 2.

##  Example Sensor data

* If we want to obtain shake value from ax gravity we can use the method getShakeSensors

`Disposable mShakeDisposable = mSensorHelper.getShakeSensors(Sensor.TYPE_ACCELEROMETER, shakeObserverResult);`


`
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
                .map(coreSensorEvent -> {
                    SensorEvent event = coreSensorEvent.getSensorEvent();
                    float ax = event.values[0];
                    float ay = event.values[1];
                    float az = event.values[2];
                    double magnitudeSquared = ax * ax;
                    float linear = az * az + ay * ay;
                    if (magnitudeSquared >= linear) {
                        return magnitudeSquared > DEFAULT_ACCELERATION_SHAKE ? true : false;
                    }
                    return false;
                })
                .subscribe(coreSensorEvent -> resultObserver.onNext(coreSensorEvent), throwable -> {
                    if (throwable instanceof SensorNotFoundException) {
                        SensorNotFoundException sensorNotFoundException = (SensorNotFoundException) throwable;
                        resultObserver.onError(sensorNotFoundException);
                    }
                });
    }
    
    `
