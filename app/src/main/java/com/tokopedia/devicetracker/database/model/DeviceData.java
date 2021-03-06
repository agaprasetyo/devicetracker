package com.tokopedia.devicetracker.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tokopedia.devicetracker.data.Device;
import com.tokopedia.devicetracker.database.DbContract;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
@DatabaseTable(tableName = DbContract.DeviceData.TABLE_NAME)
public class DeviceData extends Device {
    private static final String TAG = DeviceData.class.getSimpleName();
    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_DELETED = 2;


    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true, columnName = DbContract.ID)
    private int id;
    @DatabaseField(columnName = DbContract.DeviceData.BORROWED)
    private boolean borrowed = false;
    @DatabaseField(columnName = DbContract.DeviceData.N_STATUS)
    private int nStatus;

//    @DatabaseField(foreign = true, columnName = DbContract.DeviceData.BORROW_DATA, foreignAutoCreate = true, foreignAutoRefresh = true)
//    private BorrowData borrowData;

    public DeviceData() {
    }


    public DeviceData(Device data) {
        this.deviceName = data.getDeviceName();
        this.deviceDesc = data.getDeviceDesc();
        this.deviceModel = data.getDeviceModel();
        this.devicePicPath = data.getDevicePicPath();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public int getnStatus() {
        return nStatus;
    }

    public void setnStatus(int nStatus) {
        this.nStatus = nStatus;
    }

    //    public BorrowData getBorrowData() {
//        return borrowData;
//    }
//
//    public void setBorrowData(BorrowData borrowData) {
//        this.borrowData = borrowData;
//    }
//
    public DeviceData(Parcel in) {
        super(in);
        id = in.readInt();
        borrowed = in.readByte() != 0x00;
        nStatus = in.readInt();
        // borrowData = (BorrowData) in.readValue(BorrowData.class.getClassLoader());
    }
//

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(id);
        dest.writeByte((byte) (borrowed ? 0x01 : 0x00));
        dest.writeInt(nStatus);
        //    dest.writeValue(borrowData);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DeviceData> CREATOR = new Parcelable.Creator<DeviceData>() {
        @Override
        public DeviceData createFromParcel(Parcel in) {
            return new DeviceData(in);
        }

        @Override
        public DeviceData[] newArray(int size) {
            return new DeviceData[size];
        }
    };

    public void setNewData(DeviceData newDeviceData) {
        this.id = newDeviceData.getId();
        this.borrowed = newDeviceData.isBorrowed();
        //    this.borrowData = newDeviceData.getBorrowData();
        this.nStatus = newDeviceData.getnStatus();
        this.deviceName = newDeviceData.getDeviceName();
        this.deviceDesc = newDeviceData.getDeviceDesc();
        this.deviceModel = newDeviceData.getDeviceModel();
    }
}
