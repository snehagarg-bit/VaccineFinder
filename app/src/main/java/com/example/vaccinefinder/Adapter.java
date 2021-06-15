package com.example.vaccinefinder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

  private ArrayList<Information> info;

    public Adapter(ArrayList<Information> info) {
        this.info=info;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_view, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
         Information Information =info.get(position);
        holder.name.setText(Information.getCenterName());
        holder.centerAddressTV.setText(Information.getCenterAddress());
        holder.centerTimingsTV.setText("From:"+Information.getCenterFromTiming()+" "+"To:"+Information.getCenterToTiming());
        holder.vaccineNameTV.setText(Information.getVaccineName());
        holder.ageLimitTV.setText("Age Limit:"+Information.getAgeLimit());
        holder.feeTypeTV.setText(Information.getFeeType());
        holder.availabilityTV.setText("Availability"+Information.getAvailableCapacity());
    }

    @Override
    public int getItemCount() {
        return info== null? 0: info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  TextView name;
        public  TextView centerAddressTV;
        public  TextView centerTimingsTV ;
        public  TextView vaccineNameTV  ;
        public  TextView ageLimitTV  ;
        public  TextView feeTypeTV ;
        public  TextView availabilityTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_center_name);
           centerAddressTV = itemView.findViewById(R.id.tv_center_address);
         centerTimingsTV = itemView.findViewById (R.id.tv_center_timing);
            vaccineNameTV = itemView.findViewById(R.id.tv_vaccine_name);
             ageLimitTV = itemView.findViewById (R.id.tv_age_limit);
            feeTypeTV = itemView.findViewById (R.id.tv_fee_type);
         availabilityTV = itemView.findViewById(R.id.tv_availability);
        }
    }
}

