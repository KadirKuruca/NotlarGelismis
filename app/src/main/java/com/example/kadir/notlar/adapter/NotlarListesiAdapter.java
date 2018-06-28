package com.example.kadir.notlar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kadir.notlar.Database.Notlar;
import com.example.kadir.notlar.R;

import java.util.ArrayList;

public class NotlarListesiAdapter extends RecyclerView.Adapter<NotlarListesiAdapter.NotHolder> {

    LayoutInflater mInflater;
    ArrayList<Notlar> tumNotlar;

    public NotlarListesiAdapter(Context context, ArrayList<Notlar> gelenNotlar){
        mInflater = LayoutInflater.from(context);
        tumNotlar = gelenNotlar;
    }

    public NotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Herbir satırın oluşturulmasını sağlar.

        View view = mInflater.inflate(R.layout.tek_satir_notlar,parent,false);
        NotHolder holder = new NotHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotHolder holder, int position) {
        //View lara gelecek verilerin ataması yapılır.

        holder.mNotIcerik.setText(tumNotlar.get(position).getNotIcerik());
        holder.mNotTarih.setText(""+tumNotlar.get(position).getId());
    }

    @Override
    public int getItemCount() {
        //Listenin kaç elemandan oluştuğunu tutar.
        return tumNotlar.size();
    }

    public static class NotHolder extends RecyclerView.ViewHolder{

        //Ögelerin tanımlanması ve atamalarının yapıldığı yer

        TextView mNotIcerik,mNotTarih;

        public NotHolder(View itemView) {
            super(itemView);

            mNotIcerik = itemView.findViewById(R.id.tek_satir_not);
            mNotTarih = itemView.findViewById(R.id.tek_satir_tarih);
        }
    }


}
