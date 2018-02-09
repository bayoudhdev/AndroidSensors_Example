package com.ier.core.sensor.event;

import io.reactivex.functions.Predicate;

/**
 * Created by mohamed.bayoudh on 01/02/2018.
 */
public class CoreSensorFilter {
    /**
     * Predicate, which can be used in filter(...) method from RxJava
     * to filter all events in which sensor value has changed
     *
     * @return BiPredicate<CoreSensorEvent, Boolean> predicate indicating if sensor value has
     * changed
     */
    public static Predicate<CoreSensorEvent> filterSensorChanged() {
        return event -> event.isSensorChanged();
    }

    /**
     * Predicate, which can be used in filter(...) method from RxJava
     * to filter all events in which accuracy value has changed
     *
     * @return BiPredicate<CoreSensorEvent, Boolean> predicate indicating if accuracy value has
     * changed
     */
    public static Predicate<CoreSensorEvent> filterAccuracyChanged() {
        return event -> event.isAccuracyChanged();
    }
}
