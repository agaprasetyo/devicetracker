package com.tokopedia.devicetracker.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.google.zxing.Result;
import com.tokopedia.devicetracker.R;
import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.ui.mainadmin.presenters.QRScannerDialogPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class QRScannerValidatorAdminDialog extends Dialog implements QRScannerDialogPresenter.View, ZXingScannerView.ResultHandler {
    private static final String TAG = QRScannerValidatorAdminDialog.class.getSimpleName();

    private QRScannerDialogPresenter presenter;

    @Bind(R.id.zxing_view)
    ZXingScannerView zXingScannerView;
    private Callback dialogCallback;

    public QRScannerValidatorAdminDialog(Context context, Callback dialogCallback) {
        super(context, R.style.FullScreenDialog);
        this.dialogCallback = dialogCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new QRScannerDialogPresenter(this, dialogCallback);
        setTitle("Scan Kartu Anda");
        setContentView(R.layout.dialog_qr_scanner);
        ButterKnife.bind(this);
        presenter.initialize();
        setCanceledOnTouchOutside(false);
    }


    @Override
    public void closeDialog() {
        zXingScannerView.stopCamera();
        dismiss();
    }

    @Override
    public void setAttributeVar() {
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera(1);
    }

    @Override
    public void handleResult(Result result) {
        presenter.processQRResult(result.toString());
    }


    public interface Callback {

        void onSuccessValidation(PersonData personData);

        void onErrorValidation(String message);
    }
}
