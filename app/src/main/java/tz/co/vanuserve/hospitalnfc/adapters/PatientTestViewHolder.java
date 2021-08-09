package tz.co.vanuserve.hospitalnfc.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tz.co.vanuserve.hospitalnfc.R;

public class PatientTestViewHolder extends RecyclerView.ViewHolder {
    public TextView name,disease,room,doctor;

    public PatientTestViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name_test);
        disease=itemView.findViewById(R.id.disease_test);
        room=itemView.findViewById(R.id.room_test);
        doctor=itemView.findViewById(R.id.patient_doctor_test);
    }
}
