package com.ier.core;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.ier.core.sensor.helpers.CoreSensorsHelper;
import com.ier.core.sensor.event.CoreSensorEvent;
import com.ier.core.sensor.event.CoreSensorFilter;
import com.ier.core.sensor.helpers.SensorHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Flowable;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mohamed.bayoudh on 01/02/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class SensorHelperTest {
    @Mock
    Context mContext;
    @Mock
    SensorManager mSensorManager;
    @Mock
    Sensor mSensor;
    CoreSensorsHelper mCoreSensors;
    SensorHelper mSensorHelper;

    @Before
    public void setup() {
        when(mContext.getSystemService(anyString())).thenReturn(mSensorManager);
        when(mSensorManager.getDefaultSensor(anyInt())).thenReturn(mSensor);
        mCoreSensors = new CoreSensorsHelper(mContext);
        verify(mContext, times(1)).getSystemService(anyString());
        mSensorHelper = new SensorHelper(mSchedulersFacade, mCoreSensors);
    }

    @Test
    public void testGetLightSensors() {
        Flowable<CoreSensorEvent> lightSenDisposable = mCoreSensors.observeSensor(Sensor.TYPE_LIGHT)
                .filter(CoreSensorFilter.filterSensorChanged());
        verify(mSensorManager, atLeast(1)).getDefaultSensor(anyInt());
        assertNotNull(lightSenDisposable);
    }

}
