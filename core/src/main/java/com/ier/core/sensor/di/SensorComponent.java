package com.ier.core.sensor.di;

import com.ier.core.sensor.helpers.CoreSensorsHelper;
import com.ier.core.sensor.helpers.SensorHelper;

import dagger.Component;

/**
 * Created by mohamed.bayoudh on 08/02/2018.
 */
@SensorsScope
@Component(modules = SensorModule.class)
public interface SensorComponent {

    SensorHelper getSensorHelper();

    CoreSensorsHelper getCoreSensorsHelper();
}
