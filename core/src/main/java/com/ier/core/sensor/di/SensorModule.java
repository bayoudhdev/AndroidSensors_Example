package com.ier.core.sensor.di;

import com.ier.core.sensor.helpers.CoreSensorsHelper;
import com.ier.core.sensor.helpers.SensorHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohamed.bayoudh on 08/02/2018.
 */
@Module
public class SensorModule {

    private final SensorHelper mSensorHelper;
    private final CoreSensorsHelper mCoreSensorsHelper;

    public SensorModule(SensorHelper sensorHelper, CoreSensorsHelper coreSensorsHelper) {
        this.mSensorHelper = sensorHelper;
        this.mCoreSensorsHelper = coreSensorsHelper;
    }

    @Provides
    @SensorsScope
    SensorHelper getSensorHelper() {
        return mSensorHelper;
    }

    @Provides
    @SensorsScope
    CoreSensorsHelper getCoreSensorsHelper() {
        return mCoreSensorsHelper;
    }
}
