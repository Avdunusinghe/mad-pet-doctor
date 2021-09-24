package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DailyReportModal implements Parcelable {
    private String Id;
    private String Date;
    private int NumberOfAppointments;
    private int AppointmentFee;
    private int Fee;

    public DailyReportModal(){

    }

    public DailyReportModal(String id, String date, int numberOfAppointments, int appointmentFee, int fee) {
        Id = id;
        Date = date;
        NumberOfAppointments = numberOfAppointments;
        AppointmentFee = appointmentFee;
        Fee = fee;
    }

    protected DailyReportModal(Parcel in) {
        Id = in.readString();
        Date = in.readString();
        NumberOfAppointments = in.readInt();
        AppointmentFee = in.readInt();
        Fee = in.readInt();
    }

    public static final Creator<DailyReportModal> CREATOR = new Creator<DailyReportModal>() {
        @Override
        public DailyReportModal createFromParcel(Parcel in) {
            return new DailyReportModal(in);
        }

        @Override
        public DailyReportModal[] newArray(int size) {
            return new DailyReportModal[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getNumberOfAppointments() {
        return NumberOfAppointments;
    }

    public void setNumberOfAppointments(int numberOfAppointments) {
        NumberOfAppointments = numberOfAppointments;
    }

    public int getAppointmentFee() {
        return AppointmentFee;
    }

    public void setAppointmentFee(int appointmentFee) {
        AppointmentFee = appointmentFee;
    }

    public int getFee() {
        return Fee;
    }

    public void setFee(int fee) {
        Fee = fee;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Id);
        parcel.writeString(Date);
        parcel.writeInt(NumberOfAppointments);
        parcel.writeInt(AppointmentFee);
        parcel.writeInt(Fee);
    }
}
