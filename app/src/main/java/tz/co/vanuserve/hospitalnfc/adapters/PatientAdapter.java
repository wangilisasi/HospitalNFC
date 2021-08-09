package tz.co.vanuserve.hospitalnfc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tz.co.vanuserve.hospitalnfc.R;
import tz.co.vanuserve.hospitalnfc.models.PatientModel;


public class PatientAdapter extends RecyclerView.Adapter <PatientAdapter.PatientViewHolder> {
    List<PatientModel> categoryModelList;
    PatientViewHolder.OnCategoryClickListener mOnCategoryClickListener;

    public PatientAdapter(List<PatientModel> categoryModelList, PatientViewHolder.OnCategoryClickListener mOnCategoryClickListener) {
        this.categoryModelList = categoryModelList;
        this.mOnCategoryClickListener=mOnCategoryClickListener;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item,parent,false);
        return new PatientViewHolder(view,mOnCategoryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        holder.patientName.setText(categoryModelList.get(position).getPatientName());
        holder.patientRoom.setText(categoryModelList.get(position).getRoomNo());
        holder.patientDisease.setText(categoryModelList.get(position).getDisease());
        holder.patientDoctor.setText(categoryModelList.get(position).getDoctor());
        holder.patientImage.setImageResource(categoryModelList.get(position).getPatientImage());
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }


    public static class PatientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView patientImage;
        TextView patientName;
        TextView patientRoom;
        TextView patientDisease;
        TextView patientDoctor;
        OnCategoryClickListener onCategoryClickListener;

        public PatientViewHolder(@NonNull View itemView,OnCategoryClickListener onCategoryClickListener) {
            super(itemView);
            patientImage=itemView.findViewById(R.id.patient_image);
            patientName=itemView.findViewById(R.id.patient_name);
            patientRoom=itemView.findViewById(R.id.patient_room);
            patientDisease=itemView.findViewById(R.id.patient_disease);
            patientDoctor=itemView.findViewById(R.id.patient_doctor);
            this.onCategoryClickListener=onCategoryClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCategoryClickListener.onCategoryClick(getAdapterPosition());
        }

        public interface OnCategoryClickListener{
            void onCategoryClick(int position);
        }
    }
}
