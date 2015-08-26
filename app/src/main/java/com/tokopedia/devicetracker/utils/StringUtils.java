package com.tokopedia.devicetracker.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public class StringUtils {
    private static final String TAG = StringUtils.class.getSimpleName();

    public static String timeStringIND(long timeMilis) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", new Locale("in_ID"));
        return sdf.format(new Date(timeMilis));
    }
}
