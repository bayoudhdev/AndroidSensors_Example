package ier.demosensors.com.application;

import android.app.Application;

import com.ier.core.sensor.di.SensorsModuleDI;

import ier.demosensors.com.di.DaggerIerModuleComponent;
import ier.demosensors.com.di.IerModule;
import ier.demosensors.com.di.IerModuleComponent;

/**
 * Created by mohamed.bayoudh on 08/02/2018.
 */

public class SensorsApplication extends Application {
    IerModuleComponent mIerModuleComponent;
    SensorsModuleDI mSensorsModuleDI;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    public IerModuleComponent getIerModuleComponent() {
        return mIerModuleComponent;
    }

    /**
     * Method used to init all injection and components
     */
    public void initComponent() {
        mSensorsModuleDI = new SensorsModuleDI(this);
        mIerModuleComponent = DaggerIerModuleComponent.builder()
                .sensorComponent(mSensorsModuleDI.getSensorComponent())
                .ierModule(new IerModule(this))
                .build();
    }
}
