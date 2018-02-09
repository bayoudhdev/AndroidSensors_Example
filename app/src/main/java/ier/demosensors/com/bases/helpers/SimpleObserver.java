package ier.demosensors.com.bases.helpers;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class SimpleObserver<T> implements Observer<T> {
    /**
     * Provides the Observer with the means of cancelling (disposing) the
     * connection (channel) with the Observable in both
     * synchronous (from within {@link #onNext(Object)}) and asynchronous manner.
     *
     * @param d the Disposable instance whose {@link Disposable#dispose()} can
     *          be called anytime to cancel the connection
     * @since 2.0
     */
    @Override
    public void onSubscribe(Disposable d) {
    }

    /**
     * Provides the Observer with a new item to observe.
     * <p>
     * The {@link Observable} may call this method 0 or more times.
     * <p>
     * The {@code Observable} will not call this method again after it calls either {@link #onComplete} or
     * {@link #onError}.
     *
     * @param t the item emitted by the Observable
     */
    @Override
    public void onNext(T t) {
    }

    /**
     * Notifies the Observer that the {@link Observable} has experienced an error condition.
     * <p>
     * If the {@link Observable} calls this method, it will not thereafter call {@link #onNext} or
     * {@link #onComplete}.
     *
     * @param e the exception encountered by the Observable
     */
    @Override
    public void onError(Throwable e) {
        Timber.e(e, e.getMessage());
    }

    /**
     * Notifies the Observer that the {@link Observable} has finished sending push-based notifications.
     * <p>
     * The {@link Observable} will not call this method if it calls {@link #onError}.
     */
    @Override
    public void onComplete() {
    }

}
