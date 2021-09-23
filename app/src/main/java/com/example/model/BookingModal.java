package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.type.Date;
import com.google.type.DateTime;

import java.sql.Time;

public class BookingModal implements Parcelable {

    private String AnimalName;
    private String OwnerName;
    private String OwnerPhone;
    private String Address;
    private String AppointmentDate;
    private String AppointmentTime;
    private String BookingId;

    public BookingModal(){

    }

    public BookingModal(String animalName, String ownerName, String ownerPhone, String address, String appointmentDate, String appointmentTime, String bookingId) {
        AnimalName = animalName;
        OwnerName = ownerName;
        OwnerPhone = ownerPhone;
        Address = address;
        AppointmentDate = appointmentDate;
        AppointmentTime = appointmentTime;
        BookingId = bookingId;
    }

    protected BookingModal(Parcel in) {
        AnimalName = in.readString();
        OwnerName = in.readString();
        OwnerPhone = in.readString();
        Address = in.readString();
        AppointmentDate = in.readString();
        AppointmentTime = in.readString();
        BookingId = in.readString();
    }

    public static final Creator<BookingModal> CREATOR = new Creator<BookingModal>() {
        @Override
        public BookingModal createFromParcel(Parcel in) {
            return new BookingModal(in);
        }

        @Override
        public BookingModal[] newArray(int size) {
            return new BookingModal[size];
        }
    };

    public String getAnimalName() {
        return AnimalName;
    }

    public void setAnimalName(String animalName) {
        AnimalName = animalName;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getOwnerPhone() {
        return OwnerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        OwnerPhone = ownerPhone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        AppointmentTime = appointmentTime;
    }

    public String getBookingId() {
        return BookingId;
    }

    public void setBookingId(String bookingId) {
        BookingId = bookingId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(AnimalName);
        parcel.writeString(OwnerName);
        parcel.writeString(OwnerPhone);
        parcel.writeString(Address);
        parcel.writeString(AppointmentDate);
        parcel.writeString(AppointmentTime);
        parcel.writeString(BookingId);
    }
}
