package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ScheduleModal implements Parcelable {
    private String Id;
    private String DoctorName;
    private String Date;
    private String Time;
    private String ScheduleId;

    public ScheduleModal(){

    }

    public ScheduleModal(String id, String doctorName, String date, String time, String scheduleId) {
        Id = id;
        DoctorName = doctorName;
        Date = date;
        Time = time;
        ScheduleId = scheduleId;
    }

    protected ScheduleModal(Parcel in) {
        Id = in.readString();
        DoctorName = in.readString();
        Date = in.readString();
        Time = in.readString();
        ScheduleId = in.readString();
    }

    public static final Creator<ScheduleModal> CREATOR = new Creator<ScheduleModal>() {
        @Override
        public ScheduleModal createFromParcel(Parcel in) {
            return new ScheduleModal(in);
        }

        @Override
        public ScheduleModal[] newArray(int size) {
            return new ScheduleModal[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getScheduleId() {
        return ScheduleId;
    }

    public void setScheduleId(String scheduleId) {
        ScheduleId = scheduleId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Id);
        parcel.writeString(DoctorName);
        parcel.writeString(Date);
        parcel.writeString(Time);
        parcel.writeString(ScheduleId);
    }
}
