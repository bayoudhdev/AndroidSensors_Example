# AndroidSensors_Example
AndroidSensors is a simple library, that acts as a wrapper around the Android Sensor's system, converting the
stream of sensor data, into a Flowable that emits the same events, so that you can combine more sources of data for more 
complex operations,  RxJava2 compatible, Dagger 2.


Example Sensor data:

* If we want to obtain shake value from ax gravity we can use the method getShakeSensors

Disposable mShakeDisposable = mSensorHelper.getShakeSensors(Sensor.TYPE_ACCELEROMETER, shakeObserverResult);


    
    
    
