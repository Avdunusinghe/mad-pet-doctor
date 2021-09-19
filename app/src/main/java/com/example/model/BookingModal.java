package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingModal implements Parcelable {

    private String AnimalId;
    private String AnimalName;
    private String OwnerName;
    private String OwnerPhone;
    private String Address;
    private String BookingId;

    public BookingModal(){

    }

    public BookingModal(String animalId, String animalName, String ownerName, String ownerPhone, String address, String bookingId) {
        AnimalId = animalId;
        AnimalName = animalName;
        OwnerName = ownerName;
        OwnerPhone = ownerPhone;
        Address = address;
        BookingId = bookingId;
    }

    protected BookingModal(Parcel in) {
        AnimalId = in.readString();
        AnimalName = in.readString();
        OwnerName = in.readString();
        OwnerPhone = in.readString();
        Address = in.readString();
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

    public String getAnimalId() {
        return AnimalId;
    }

    public void setAnimalId(String animalId) {
        AnimalId = animalId;
    }

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
        parcel.writeString(AnimalId);
        parcel.writeString(AnimalName);
        parcel.writeString(OwnerName);
        parcel.writeString(OwnerPhone);
        parcel.writeString(Address);
        parcel.writeString(BookingId);
    }
}
