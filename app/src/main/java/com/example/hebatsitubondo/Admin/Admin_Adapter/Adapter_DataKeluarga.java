package com.example.hebatsitubondo.Admin.Admin_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hebatsitubondo.Admin.Model_Admin.Model_Keluarga;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_StoryTelling;
import com.example.hebatsitubondo.R;

import java.util.List;

public class Adapter_DataKeluarga extends RecyclerView.Adapter<Adapter_DataKeluarga.MyViewHolderKeluarga> {

    private Context context;
    private List<Model_Keluarga> data;

    public Adapter_DataKeluarga(Context context, List<Model_Keluarga> data ) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolderKeluarga onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_data_keluarga,parent,false);
//        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_keluarga,parent,false);
        return new MyViewHolderKeluarga(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderKeluarga holder, int position) {
        final Model_Keluarga modelKeluarga = data.get(position);
        holder.Nama.setText(modelKeluarga.getNama());
        holder.Nama_Status.setText(modelKeluarga.getNama_status());
        holder.Nama_Kepala_Keluarga.setText(modelKeluarga.getNama_kepala_keluarga());
        holder.Alamat_keluarga.setText(modelKeluarga.getAlamat_keluarga());
        holder.ttl.setText(modelKeluarga.getTtl());
        holder.Pekerjaan.setText(modelKeluarga.getPekerjaan());
        holder.No_Handphone.setText(modelKeluarga.getNo_handphone());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolderKeluarga extends RecyclerView.ViewHolder {
        TextView Nama;
        TextView Nama_Kepala_Keluarga;
        TextView Nama_Status;
        TextView Alamat_keluarga;
        TextView ttl;
        TextView Pekerjaan;
        TextView No_Handphone;

        public MyViewHolderKeluarga(@NonNull View itemView) {
            super(itemView);

            Nama = itemView.findViewById(R.id.Nama);
            Nama_Kepala_Keluarga = itemView.findViewById(R.id.Nama_Kepala_Keluarga);
            Nama_Status = itemView.findViewById(R.id.Nama_Status);
            Alamat_keluarga = itemView.findViewById(R.id.Alamat_Keluarga);
            ttl = itemView.findViewById(R.id.Ttl);
            Pekerjaan = itemView.findViewById(R.id.Pekerjaan);
            No_Handphone = itemView.findViewById(R.id.No_Handphone);
        }
    }
}
