package com.tokopedia.devicetracker.ui.administration.dialog;

import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.interactors.interactor.ValidationAdminInteractor;
import com.tokopedia.devicetracker.interactors.iteractorimpl.ValidationAdminIteractorImpl;
import com.tokopedia.devicetracker.interactors.listener.OnValidationAdminFinishedListener;
import com.tokopedia.devicetracker.ui.main.presenters.Presenter;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class QRScannerDialogPresenter extends Presenter implements OnValidationAdminFinishedListener {
    private static final String TAG = QRScannerDialogPresenter.class.getSimpleName();

    private QRScannerValidatorAdminDialog.Callback callback;
    private ValidationAdminInteractor validationAdminInteractor;
    private View view;


    public QRScannerDialogPresenter(QRScannerValidatorAdminDialog view, QRScannerValidatorAdminDialog.Callback dialogCallback) {
        this.view = view;
        this.callback = dialogCallback;
        this.validationAdminInteractor = new ValidationAdminIteractorImpl(this);
    }

    @Override
    public void initialize() {
        view.setAttributeVar();

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void processQRResult(String result) {
        //view.closeDialog();
        validationAdminInteractor.validationQRResult(result);
    }

    @Override
    public void onValidationValid(PersonData personData) {
        callback.onSuccessValidation(personData);
        view.closeDialog();
    }

    @Override
    public void onValidationInvalid(String message) {
        callback.onErrorValidation(message);
        view.closeDialog();
    }


    public interface View {
        void closeDialog();

        void setAttributeVar();
    }
}
