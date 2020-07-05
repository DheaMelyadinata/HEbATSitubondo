package com.example.hebatsitubondo.Orangtua.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Kegiatan;
import com.example.hebatsitubondo.Orangtua.AgendaKegiatan_Detail;
import com.example.hebatsitubondo.Orangtua.Model.TemaPortofolioTM;
import com.example.hebatsitubondo.Orangtua.Portofolio_Anak;
import com.example.hebatsitubondo.R;

import java.util.List;

public class Adapter_AgendaKegiatan extends RecyclerView.Adapter<Adapter_AgendaKegiatan.myViewHolder1> {

    private Context mContext;
    private static List<Model_Kegiatan> mData;

    public Adapter_AgendaKegiatan(Context mContext, List<Model_Kegiatan> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_agenda_kegiatan_read,parent,false);
        return new myViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder1 holder, int position) {

        final Model_Kegiatan modelKegiatan = mData.get(position);

        holder.read_tvJenis_Kegiatan.setText(mData.get(position).getJenisKegiatan());
        holder.read_tvNama_Kegiatan.setText(mData.get(position).getNamaKegiatan());
        holder.read_tvWaktu_Kegiatan.setText(mData.get(position).getWaktu_kegiatan());
        holder.read_tvTempat.setText(mData.get(position).getTempat());

        Glide.with(holder.itemView.getContext())
                .load("http://192.168.43.62/API_HEbATSitubondo/public/" + modelKegiatan.getPhotoKegiatan())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.read_imgKegiatan);

        holder.btn_set_detail_ak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(holder.itemView.getContext(), AgendaKegiatan_Detail.class);
                i.putExtra("photo_dokumentasi", modelKegiatan.getJenisKegiatan());
                i.putExtra("deskripsi_kegiatan", modelKegiatan.getNamaKegiatan());
                i.putExtra("waktu_kegiatan", modelKegiatan.getWaktu_kegiatan());;
                i.putExtra("tempat", modelKegiatan.getTempat());
                i.putExtra("photo_kegiatan", modelKegiatan.getPhotoKegiatan());

                holder.itemView.getContext().startActivity(i);
            }
        });

    }
        @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder1 extends RecyclerView.ViewHolder {

        TextView read_tvJenis_Kegiatan;
        TextView read_tvNama_Kegiatan;
        TextView read_tvWaktu_Kegiatan;
        TextView read_tvTempat;
        ImageView read_imgKegiatan;
        Button btn_set_detail_ak;

        public myViewHolder1(@NonNull View itemView) {
            super(itemView);

            read_tvJenis_Kegiatan = itemView.findViewById(R.id.read_tvJenis_Kegiatan);
            read_tvNama_Kegiatan = itemView.findViewById(R.id.read_tvNama_Kegiatan);
            read_tvWaktu_Kegiatan = itemView.findViewById(R.id.read_tvWaktu_Kegiatan);
            read_tvTempat = itemView.findViewById(R.id.read_tvTempat);
            read_imgKegiatan = itemView.findViewById(R.id.read_imgKegiatan);
            btn_set_detail_ak = itemView.findViewById(R.id.btn_set_detail_ak);
        }
    }
}
