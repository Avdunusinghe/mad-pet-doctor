package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactModal  implements Parcelable {
    private String Name;
    private String Email;
    private String Message;
    private String ContactId;

    public ContactModal(){

    }

    public ContactModal(String name, String email, String message, String contactId) {
        Name = name;
        Email = email;
        Message = message;
        ContactId = contactId;
    }

    protected ContactModal(Parcel in) {
        Name = in.readString();
        Email = in.readString();
        Message = in.readString();
        ContactId = in.readString();
    }

    public static final Creator<ContactModal> CREATOR = new Creator<ContactModal>() {
        @Override
        public ContactModal createFromParcel(Parcel in) {
            return new ContactModal(in);
        }

        @Override
        public ContactModal[] newArray(int size) {
            return new ContactModal[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getContactId() {
        return ContactId;
    }

    public void setContactId(String contactId) {
        ContactId = contactId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Email);
        parcel.writeString(Message);
        parcel.writeString(ContactId);
    }
}
