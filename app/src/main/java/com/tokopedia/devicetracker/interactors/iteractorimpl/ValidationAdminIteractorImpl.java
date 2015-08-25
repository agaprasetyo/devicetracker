package com.tokopedia.devicetracker.interactors.iteractorimpl;

import com.tokopedia.devicetracker.database.model.PersonData;
import com.tokopedia.devicetracker.interactors.listener.OnValidationAdminFinishedListener;
import com.tokopedia.devicetracker.interactors.interactor.ValidationAdminInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angga.Prasetiyo on 25/08/2015.
 */
public class ValidationAdminIteractorImpl implements ValidationAdminInteractor {
    private static final String TAG = ValidationAdminIteractorImpl.class.getSimpleName();
    private OnValidationAdminFinishedListener listener;
    private List<PersonData> personAdmin = new ArrayList<>();

    public ValidationAdminIteractorImpl(OnValidationAdminFinishedListener listener) {
        this.listener = listener;
        personAdmin.add(new PersonData(65, "https://www.tokopedia.com/team/65", "Rico Harisin"));
        personAdmin.add(new PersonData(468, "https://www.tokopedia.com/team/468", "Angga Prasetiyo"));
    }

    @Override
    public void validationQRResult(String qrResult) {
        boolean isValid = false;
        for (PersonData personData : personAdmin) {
            if (personData.getUrl().equals(qrResult)) {
                listener.onValidationValid(personData);
                isValid = true;
                break;
            }
        }
        if (!isValid)
            listener.onValidationInvalid("Anda tidak memiliki permission untuk management data!");
    }


}
