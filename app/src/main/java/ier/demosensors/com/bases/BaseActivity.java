package ier.demosensors.com.bases;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import ier.demosensors.com.application.SensorsApplication;
import ier.demosensors.com.di.IerModuleComponent;

/**
 * Created by mohamed.bayoudh on 08/02/2018.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    public abstract void injectDependencies();

    /**
     * Lifecycle Method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public SensorsApplication getSensorsApplication() {
        return (SensorsApplication) getApplication();
    }

    public IerModuleComponent getIerModuleComponent() {
        return getSensorsApplication().getIerModuleComponent();
    }
}
