package com.ier.core.sensor.di;

import android.content.Context;

import com.ier.core.sensor.SchedulersFacade;
import com.ier.core.sensor.helpers.CoreSensorsHelper;
import com.ier.core.sensor.helpers.SensorHelper;

/**
 * Created by mohamed.bayoudh on 08/02/2018.
 */
public class SensorsModuleDI {
    private SensorComponent mSensorComponent;

    public SensorsModuleDI(Context context){
        CoreSensorsHelper coreSensorsHelper=new CoreSensorsHelper(context);
        SchedulersFacade mSchedulersFacade=new SchedulersFacade();
        SensorHelper sensorHelper=new SensorHelper(mSchedulersFacade, coreSensorsHelper);
        SensorModule sensorModule=new SensorModule(sensorHelper,coreSensorsHelper);
        mSensorComponent=DaggerSensorComponent.builder()
                .sensorModule(sensorModule)
                .build();
    }

    public SensorComponent getSensorComponent() {
        return mSensorComponent;
    }
}
