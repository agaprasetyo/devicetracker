package com.tokopedia.devicetracker.database.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
public class PersonData implements Parcelable {
    private static final String TAG = PersonData.class.getSimpleName();

    private int id;
    private String url;
    private String name;


    protected PersonData(Parcel in) {
        id = in.readInt();
        url = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PersonData> CREATOR = new Parcelable.Creator<PersonData>() {
        @Override
        public PersonData createFromParcel(Parcel in) {
            return new PersonData(in);
        }

        @Override
        public PersonData[] newArray(int size) {
            return new PersonData[size];
        }
    };

}
