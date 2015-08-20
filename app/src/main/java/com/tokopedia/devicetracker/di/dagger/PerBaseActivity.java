package com.tokopedia.devicetracker.di.dagger;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.inject.Scope;

/**
 * Created by Angga.Prasetiyo on 20/08/2015.
 */
@Scope
@Retention(RUNTIME)
public @interface PerBaseActivity {
}
