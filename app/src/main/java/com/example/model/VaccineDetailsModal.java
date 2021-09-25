package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RadioGroup;

public class VaccineDetailsModal implements Parcelable{
    String Vaccine_Name_Edt ;
    String Vaccine_Date_Edt ;
    int No_of_Vaccines;
    int Vaccine_Price_Edt ;
    int Vaccine_Total_price;

    public VaccineDetailsModal(){}

    public VaccineDetailsModal(String vaccine_Name_Edt, String vaccine_Date_Edt, int no_of_Vaccines, int vaccine_Price_Edt, int vaccine_Total_price) {
        Vaccine_Name_Edt = vaccine_Name_Edt;
        Vaccine_Date_Edt = vaccine_Date_Edt;
        No_of_Vaccines = no_of_Vaccines;
        Vaccine_Price_Edt = vaccine_Price_Edt;
        Vaccine_Total_price = vaccine_Total_price;
    }

    protected VaccineDetailsModal(Parcel in) {
        Vaccine_Name_Edt = in.readString();
        Vaccine_Date_Edt = in.readString();
        No_of_Vaccines = in.readInt();
        Vaccine_Price_Edt = in.readInt();
        Vaccine_Total_price = in.readInt();
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

    public int getNo_of_Vaccines() {
        return No_of_Vaccines;
    }

    public void setNo_of_Vaccines(int no_of_Vaccines) {
        No_of_Vaccines = no_of_Vaccines;
    }

    public int getVaccine_Price_Edt() {
        return Vaccine_Price_Edt;
    }

    public void setVaccine_Price_Edt(int vaccine_Price_Edt) {
        Vaccine_Price_Edt = vaccine_Price_Edt;
    }

    public int getVaccine_Total_price() {
        return Vaccine_Total_price;
    }

    public void setVaccine_Total_price(int vaccine_Total_price) {
        Vaccine_Total_price = vaccine_Total_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Vaccine_Name_Edt);
        dest.writeString(Vaccine_Date_Edt);
        dest.writeInt(No_of_Vaccines);
        dest.writeInt(Vaccine_Price_Edt);
        dest.writeInt(Vaccine_Total_price);
    }
}
