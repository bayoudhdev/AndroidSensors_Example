package ier.demosensors.com.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ier.demosensors.com.application.SensorsApplication;

/**
 * Created by mohamed.bayoudh on 08/02/2018.
 */
@Module
public class IerModule {
    private final SensorsApplication mSensorsApplication;

    public IerModule(SensorsApplication mSensorsApplication) {
        this.mSensorsApplication = mSensorsApplication;
    }

    @Provides
    @IerModuleScope
    Context getContext() {
        return mSensorsApplication.getApplicationContext();
    }

    @Provides
    @IerModuleScope
    SensorsApplication getApplication() {
        return mSensorsApplication;
    }
}
