package com.tokopedia.devicetracker.app;

import android.util.Log;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class AppLog {
    private static final String TAG = AppLog.class.getSimpleName();

    public static void debug(String tag, String message) {
        Log.d("VPRESA LOG " + tag, message);
    }
}
