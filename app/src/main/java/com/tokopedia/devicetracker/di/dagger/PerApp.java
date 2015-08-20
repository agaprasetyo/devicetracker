package com.tokopedia.devicetracker.di.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Angga.Prasetiyo on 20/08/2015.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {
}
