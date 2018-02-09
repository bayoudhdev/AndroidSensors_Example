package ier.demosensors.com.di;

import com.ier.core.sensor.di.SensorComponent;

import dagger.Component;
import ier.demosensors.com.activity.MainActivity;

/**
 * Created by mohamed.bayoudh on 08/02/2018.
 */

@Component(dependencies = {SensorComponent.class}, modules = {IerModule.class})
@IerModuleScope
public interface IerModuleComponent extends SensorComponent {
    void inject(MainActivity mainActivity);
}
