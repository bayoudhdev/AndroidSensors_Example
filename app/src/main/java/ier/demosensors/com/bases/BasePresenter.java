package ier.demosensors.com.bases;

import com.ier.core.sensor.helpers.CoreSensorsHelper;
import com.ier.core.sensor.helpers.SensorHelper;

/**
 * Created by mohamed.bayoudh on 09/02/2018.
 */

public abstract class BasePresenter  implements BaseInteractor{
    protected SensorHelper mSensorHelper;
    protected CoreSensorsHelper mCoreSensorsHelper;

    public BasePresenter() {

    }

    public BasePresenter(SensorHelper sensorHelper) {
        this.mSensorHelper = sensorHelper;
    }

    public BasePresenter(SensorHelper sensorHelper, CoreSensorsHelper coreSensorsHelper) {
        this.mSensorHelper = sensorHelper;
        this.mCoreSensorsHelper = coreSensorsHelper;
    }

    public BasePresenter(CoreSensorsHelper coreSensorsHelper) {
        this.mCoreSensorsHelper = coreSensorsHelper;
    }
}
