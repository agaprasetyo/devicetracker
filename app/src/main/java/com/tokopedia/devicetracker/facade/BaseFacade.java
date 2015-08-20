package com.tokopedia.devicetracker.facade;

/**
 * Created by Angga.Prasetiyo on 19/08/2015.
 */
public abstract class BaseFacade<T> {

    private static final String TAG = BaseFacade.class.getSimpleName();



    public interface FacadeCallbackListener<T> {

        void onSuccess(T response);

        void onError(String errorMessage);
    }
}
