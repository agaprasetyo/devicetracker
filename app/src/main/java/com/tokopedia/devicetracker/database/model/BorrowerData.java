package com.tokopedia.devicetracker.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tokopedia.devicetracker.database.DbContract;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
@DatabaseTable(tableName = DbContract.BorrowerData.TABLE_NAME)
public class BorrowerData implements Parcelable {
    private static final String TAG = BorrowerData.class.getSimpleName();

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true, columnName = DbContract.ID)
    private int id;
    @DatabaseField(columnName = DbContract.BorrowerData.ID_EMPLOYEE)
    private String idEmployee;
    @DatabaseField(columnName = DbContract.BorrowerData.URL_EMPLOYEE)
    private String urlEmployee;
    @DatabaseField(columnName = DbContract.BorrowerData.NAME)
    private String name;
    @DatabaseField(columnName = DbContract.BorrowerData.DIVISION)
    private String division;
    @DatabaseField(columnName = DbContract.BorrowerData.POSITION)
    private String position;

    public BorrowerData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUrlEmployee() {
        return urlEmployee;
    }

    public void setUrlEmployee(String urlEmployee) {
        this.urlEmployee = urlEmployee;
    }

    protected BorrowerData(Parcel in) {
        id = in.readInt();
        urlEmployee = in.readString();
        idEmployee = in.readString();
        name = in.readString();
        division = in.readString();
        position = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(idEmployee);
        dest.writeString(urlEmployee);
        dest.writeString(name);
        dest.writeString(division);
        dest.writeString(position);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BorrowerData> CREATOR = new Parcelable.Creator<BorrowerData>() {
        @Override
        public BorrowerData createFromParcel(Parcel in) {
            return new BorrowerData(in);
        }

        @Override
        public BorrowerData[] newArray(int size) {
            return new BorrowerData[size];
        }
    };
}
