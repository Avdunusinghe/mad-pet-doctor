package com.example.list_view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mad_pet_doctor.R;
import com.example.model.VaccineDetailsModal;

import java.util.List;

public class VaccineReportList extends ArrayAdapter<VaccineDetailsModal> {

        private Activity context;
        List<VaccineDetailsModal> vaccineDetailsModals;

        public VaccineReportList(Activity context, List<VaccineDetailsModal> vaccineDetailsModals) {
        super(context, R.layout.vaccine_report_list, vaccineDetailsModals);
        this.context = context;
        this.vaccineDetailsModals = vaccineDetailsModals;
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.vaccine_report_list_details, null, true);
            TextView textViewVRName = (TextView) listViewItem.findViewById(R.id.VR_name_list_textview);
            TextView textViewVRDate = (TextView) listViewItem.findViewById(R.id.VR_date_list_textview);
            TextView textViewVRPrice = (TextView) listViewItem.findViewById(R.id.VR_price_list_textview);

            VaccineDetailsModal vaccineDetailsModal = vaccineDetailsModals.get(position);
            textViewVRName.setText(vaccineDetailsModal.getVaccine_Name_Edt());
            textViewVRDate.setText(vaccineDetailsModal.getVaccine_Date_Edt());
            textViewVRPrice.setText(vaccineDetailsModal.getVaccine_Price_Edt());

            return listViewItem;
        }
}
