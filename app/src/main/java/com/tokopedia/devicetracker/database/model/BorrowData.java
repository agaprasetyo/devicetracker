package com.tokopedia.devicetracker.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tokopedia.devicetracker.database.DbContract;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
@DatabaseTable(tableName = DbContract.BorrowData.TABLE_NAME)
public class BorrowData implements Parcelable {
    private static final String TAG = BorrowData.class.getSimpleName();

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true, columnName = DbContract.ID)
    private int id;
    @DatabaseField(columnName = DbContract.BorrowData.TIME_OF_BORROW)
    private long time;
    @DatabaseField(foreign = true, columnName = DbContract.BorrowData.BORROWER, foreignAutoCreate = true, foreignAutoRefresh = true)
    private BorrowerData borrowerData;

    public BorrowData() {

    }

    public BorrowData(long time, BorrowerData borrowerData) {
        this.time = time;
        this.borrowerData = borrowerData;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public BorrowerData getBorrowerData() {
        return borrowerData;
    }

    public void setBorrowerData(BorrowerData borrowerData) {
        this.borrowerData = borrowerData;
    }

    protected BorrowData(Parcel in) {
        id = in.readInt();
        time = in.readLong();
        borrowerData = (BorrowerData) in.readValue(BorrowerData.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(time);
        dest.writeValue(borrowerData);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BorrowData> CREATOR = new Parcelable.Creator<BorrowData>() {
        @Override
        public BorrowData createFromParcel(Parcel in) {
            return new BorrowData(in);
        }

        @Override
        public BorrowData[] newArray(int size) {
            return new BorrowData[size];
        }
    };
}
