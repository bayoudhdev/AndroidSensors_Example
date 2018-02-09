package ier.demosensors.com.activity;

import android.hardware.Sensor;

import com.ier.core.sensor.helpers.SensorHelper;
import com.ier.core.sensor.listener.CoreObserverResult;

import javax.inject.Inject;

import ier.demosensors.com.bases.BasePresenter;
import ier.demosensors.com.bases.utils.Preconditions;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by mohamed.bayoudh on 09/02/2018.
 */

public class MainPresenter extends BasePresenter {

    private MainInteractor.IUEventActionListener mIUEventActionListener;
    private MainInteractor.IUView mIUView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public MainPresenter(SensorHelper sensorHelper) {
        super(sensorHelper);
    }

    public void setView(@NonNull MainInteractor.IUView uiView) {
        Preconditions.checkNotNull(uiView);
        this.mIUView = uiView;
    }

    public void setAction(MainInteractor.IUEventActionListener uiAction) {
        Preconditions.checkNotNull(uiAction);
        this.mIUEventActionListener = uiAction;
    }

    public void initializeSensors() {
        Disposable mLightDisposable = mSensorHelper.getLightSensors(Sensor.TYPE_LIGHT, lightObserverResult);
        Disposable mProximityDisposable = mSensorHelper.getProximitySensors(Sensor.TYPE_PROXIMITY, proximityObserverResult);
        Disposable mChocDisposable = mSensorHelper.getChocSensors(Sensor.TYPE_ACCELEROMETER, chocObserverResult);
        Disposable mShakeDisposable = mSensorHelper.getShakeSensors(Sensor.TYPE_ACCELEROMETER, shakeObserverResult);
        mCompositeDisposable.add(mLightDisposable);
        mCompositeDisposable.add(mProximityDisposable);
        mCompositeDisposable.add(mChocDisposable);
        mCompositeDisposable.add(mShakeDisposable);
    }

    @Override
    public void resume() {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        initializeSensors();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }

    CoreObserverResult<Float> lightObserverResult = new CoreObserverResult<Float>() {
        @Override
        public void onNext(Float aFloat) {
            super.onNext(aFloat);
            mIUView.showLightSensorResult(aFloat);
        }
    };

    CoreObserverResult<Boolean> proximityObserverResult = new CoreObserverResult<Boolean>() {
        @Override
        public void onNext(Boolean aBoolean) {
            super.onNext(aBoolean);
            mIUView.showProximitySensorResult(aBoolean);
        }
    };

    CoreObserverResult<Boolean> chocObserverResult = new CoreObserverResult<Boolean>() {
        @Override
        public void onNext(Boolean aBoolean) {
            super.onNext(aBoolean);
            mIUView.showChocSensorResult(aBoolean);
        }
    };

    CoreObserverResult<Boolean> shakeObserverResult = new CoreObserverResult<Boolean>() {
        @Override
        public void onNext(Boolean aBoolean) {
            super.onNext(aBoolean);
            mIUView.showShakeSensorResult(aBoolean);
        }
    };
}
