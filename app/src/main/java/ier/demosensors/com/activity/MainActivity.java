package ier.demosensors.com.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

import ier.demosensors.com.R;
import ier.demosensors.com.bases.BaseActivity;
import ier.demosensors.com.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements MainInteractor.IUEventActionListener, MainInteractor.IUView {

    ActivityMainBinding mActivityMainBinding;

    @Inject
    MainPresenter mMainPresenter;

    @Override
    public void injectDependencies() {
        getSensorsApplication().getIerModuleComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        mMainPresenter.setAction(this);
        mMainPresenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMainPresenter.pause();
    }

    /**
     * Method used to get location
     */
    public void getLocation() {

        //  RxLocation rxLocation = new RxLocation(MainActivity.this);
        //  LocationRequest locationRequest = LocationRequest.create()
        //          .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        //          .setInterval(5000);

        //  if (ActivityCompat.checkSelfPermission(this
        //          , Manifest.permission.ACCESS_FINE_LOCATION)
        //          != PackageManager.PERMISSION_GRANTED
        //          && ActivityCompat.checkSelfPermission(this
        //          , Manifest.permission.ACCESS_COARSE_LOCATION)
        //          != PackageManager.PERMISSION_GRANTED) {
        //      return;
        //  }

        //  Disposable locationDisposable = rxLocation.location().updates(locationRequest)
        //          .flatMap(location -> rxLocation.geocoding().fromLocation(location).toObservable())
        //          .subscribeOn(Schedulers.io())
        //          .observeOn(AndroidSchedulers.mainThread())
        //          .subscribe(address -> {
        //              mActivityMainBinding.txtLng.setText(String.valueOf(address.getLongitude()));
        //              mActivityMainBinding.txtLat.setText(String.valueOf(address.getLatitude()));
        //          });

        //  mCompositeDisposable.add(locationDisposable);

    }

    @Override
    public void showLightSensorResult(Float aFloat) {
        mActivityMainBinding.idBtnLight.setBackgroundColor(ContextCompat
                .getColor(MainActivity.this, (aFloat > 5)
                ? R.color.colorYellow : R.color.colorAccent));
    }

    @Override
    public void showProximitySensorResult(Boolean aBoolean) {
        mActivityMainBinding.idBtnProximity
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, aBoolean
                        ? R.color.colorPrimary : R.color.colorAccent));
    }

    @Override
    public void showChocSensorResult(Boolean aBoolean) {
        if (aBoolean) {
            mActivityMainBinding.idBtnChoc.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
        }
    }

    @Override
    public void showShakeSensorResult(Boolean aBoolean) {
        if (aBoolean) {
            mActivityMainBinding.idBtnShake.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        //   Snackbar.make(mActivityMainBinding.id)
    }

    @Override
    public Context context() {
        return this;
    }
}
