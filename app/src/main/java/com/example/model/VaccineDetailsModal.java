package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RadioGroup;

public class VaccineDetailsModal implements Parcelable{
    String Vaccine_Name_Edt ;
    String Vaccine_Date_Edt ;
    int Vaccine_Price_Edt ;

    public VaccineDetailsModal(){}


    public VaccineDetailsModal(String vaccine_Name_Edt, String vaccine_Date_Edt, int vaccine_Price_Edt) {
        Vaccine_Name_Edt = vaccine_Name_Edt;
        Vaccine_Date_Edt = vaccine_Date_Edt;
        Vaccine_Price_Edt = vaccine_Price_Edt;
    }

    protected VaccineDetailsModal(Parcel in) {
        Vaccine_Name_Edt = in.readString();
        Vaccine_Date_Edt = in.readString();
        Vaccine_Price_Edt = in.readInt();
    }

    public static final Creator<VaccineDetailsModal> CREATOR = new Creator<VaccineDetailsModal>() {
        @Override
        public VaccineDetailsModal createFromParcel(Parcel in) {
            return new VaccineDetailsModal(in);
        }

        @Override
        public VaccineDetailsModal[] newArray(int size) {
            return new VaccineDetailsModal[size];
        }
    };

    public String getVaccine_Name_Edt() {
        return Vaccine_Name_Edt;
    }

    public void setVaccine_Name_Edt(String vaccine_Name_Edt) {
        Vaccine_Name_Edt = vaccine_Name_Edt;
    }

    public String getVaccine_Date_Edt() {
        return Vaccine_Date_Edt;
    }

    public void setVaccine_Date_Edt(String vaccine_Date_Edt) {
        Vaccine_Date_Edt = vaccine_Date_Edt;
    }

    public int getVaccine_Price_Edt() {
        return Vaccine_Price_Edt;
    }

    public void setVaccine_Price_Edt(int vaccine_Price_Edt) {
        Vaccine_Price_Edt = vaccine_Price_Edt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Vaccine_Name_Edt);
        dest.writeString(Vaccine_Date_Edt);
        dest.writeInt(Vaccine_Price_Edt);
    }
}
