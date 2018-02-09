package ier.demosensors.com.activity;

import ier.demosensors.com.bases.BaseDataView;

/**
 * Created by mohamed.bayoudh on 09/02/2018.
 */

public interface MainInteractor {

    interface IUView extends BaseDataView {
        void showLightSensorResult(Float aFloat);

        void showProximitySensorResult(Boolean aBoolean);

        void showChocSensorResult(Boolean aBoolean);

        void showShakeSensorResult(Boolean aBoolean);
    }

    interface IUEventActionListener {

    }
}
