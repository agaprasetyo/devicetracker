package com.tokopedia.devicetracker.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.tokopedia.devicetracker.database.DbContract;

/**
 * Created by Angga.Prasetiyo on 18/08/2015.
 */
public class Device implements Parcelable {
    private static final String TAG = Device.class.getSimpleName();

    @DatabaseField(columnName = DbContract.DeviceData.DEVICE_NUMBER)
    protected String deviceNumber;
    @DatabaseField(columnName = DbContract.DeviceData.DEVICE_NAME)
    protected String deviceName;
    @DatabaseField(columnName = DbContract.DeviceData.DEVICE_MODEL)
    protected String deviceModel;
    @DatabaseField(columnName = DbContract.DeviceData.DEVICE_PIC_ASSET)
    protected String devicePicAsset;
    @DatabaseField(columnName = DbContract.DeviceData.DEVICE_DESC)
    protected String deviceDesc;

    public Device() {
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceDesc() {
        return deviceDesc;
    }

    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    public String getDevicePicAsset() {
        return devicePicAsset;
    }

    public void setDevicePicAsset(String devicePicAsset) {
        this.devicePicAsset = devicePicAsset;
    }

    protected Device(Parcel in) {
        deviceNumber = in.readString();
        deviceName = in.readString();
        deviceModel = in.readString();
        devicePicAsset = in.readString();
        deviceDesc = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deviceNumber);
        dest.writeString(deviceName);
        dest.writeString(deviceModel);
        dest.writeString(devicePicAsset);
        dest.writeString(deviceDesc);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };
}
