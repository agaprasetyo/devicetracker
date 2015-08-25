package com.tokopedia.devicetracker.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tokopedia.devicetracker.database.DbContract;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
@DatabaseTable(tableName = DbContract.PersonData.TABLE_NAME)
public class PersonData implements Parcelable {
    private static final String TAG = PersonData.class.getSimpleName();

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true, columnName = DbContract.ID)
    private int id;
    @DatabaseField(columnName = DbContract.PersonData.URL)
    private String url;
    @DatabaseField(columnName = DbContract.PersonData.NAME)
    private String name;

    public PersonData() {

    }

    public PersonData(int id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
