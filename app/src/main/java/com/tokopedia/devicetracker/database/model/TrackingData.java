package com.tokopedia.devicetracker.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tokopedia.devicetracker.database.DbContract;

import java.util.Date;

/**
 * Created by Angga.Prasetiyo on 24/08/2015.
 */
@DatabaseTable(tableName = DbContract.TrackingData.TABLE_NAME)
public class TrackingData implements Parcelable {
    private static final String TAG = TrackingData.class.getSimpleName();

    public static final int ACTIVITY_BORROW = 1;
    public static final int ACTIVITY_RETURN = 0;
    public static final int ACTIVITY_ADD_DEVICE = 2;
    public static final int ACTIVITY_REMOVE_DEVICE = 3;

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true, columnName = DbContract.ID)
    private int id;
    @DatabaseField(columnName = DbContract.TrackingData.TIME)
    private long time;
    @DatabaseField(columnName = DbContract.TrackingData.ACTIVITY)
    private int activity;
    @DatabaseField(foreign = true, columnName = DbContract.TrackingData.DEVICE, foreignAutoCreate = true, foreignAutoRefresh = true)
    private DeviceData device;
    @DatabaseField(foreign = true, columnName = DbContract.TrackingData.PERSON, foreignAutoCreate = true, foreignAutoRefresh = true)
    private PersonData person;

    public TrackingData() {
        this.time = new Date().getTime();
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

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public DeviceData getDevice() {
        return device;
    }

    public void setDevice(DeviceData device) {
        this.device = device;
    }

    public PersonData getPerson() {
        return person;
    }

    public void setPerson(PersonData person) {
        this.person = person;
    }

    protected TrackingData(Parcel in) {
        id = in.readInt();
        time = in.readLong();
        activity = in.readInt();
        device = (DeviceData) in.readValue(DeviceData.class.getClassLoader());
        person = (PersonData) in.readValue(PersonData.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(time);
        dest.writeInt(activity);
        dest.writeValue(device);
        dest.writeValue(person);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TrackingData> CREATOR = new Parcelable.Creator<TrackingData>() {
        @Override
        public TrackingData createFromParcel(Parcel in) {
            return new TrackingData(in);
        }

        @Override
        public TrackingData[] newArray(int size) {
            return new TrackingData[size];
        }
    };
}
