package ier.demosensors.com.di;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mohamed.bayoudh on 27/09/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME) public @interface IerModuleScope {
}