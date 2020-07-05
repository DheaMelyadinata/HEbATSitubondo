package com.example.hebatsitubondo.Orangtua.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Dokumentasi_Kegiatan;
import com.example.hebatsitubondo.Orangtua.Dokumentasi_Detail;
import com.example.hebatsitubondo.R;

import java.util.List;

public class Adapter_Dokumentasi extends RecyclerView.Adapter<Adapter_Dokumentasi.myViewHolder2> {

    private Context mContext;
    private static List<Model_Dokumentasi_Kegiatan> mData;


    public Adapter_Dokumentasi(Context mContext, List<Model_Dokumentasi_Kegiatan> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_dokumentasi_kegiatan_read,parent,false);
        return new myViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder2 holder, int position) {

        final Model_Dokumentasi_Kegiatan model_dokumentasi_kegiatan = mData.get(position);

        holder.read_Kegiatan_dokumentasi.setText(mData.get(position).getNamaKegiatan());

        Glide.with(holder.itemView.getContext())
                .load("http://192.168.43.62/API_HEbATSitubondo/public/" + model_dokumentasi_kegiatan.getPhotoDokumentasi())
                .apply(new RequestOptions().centerCrop().override(150, 180))
                .into(holder.read_imgKegiatan_Dokumentasi);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(holder.itemView.getContext(), Dokumentasi_Detail.class);
//                i.putExtra("id_dokumentasi_kegiatan", model_dokumentasi_kegiatan.getId_Dokumentasi());
                i.putExtra("nama_kegiatan", model_dokumentasi_kegiatan.getNamaKegiatan());
                i.putExtra("photo_dokumentasi", model_dokumentasi_kegiatan.getPhotoDokumentasi());
                i.putExtra("deskripsi_kegiatan", model_dokumentasi_kegiatan.getDeskripsiKegiatan());

                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder2 extends RecyclerView.ViewHolder {

        TextView read_Kegiatan_dokumentasi;
        ImageView read_imgKegiatan_Dokumentasi;
        CardView cardView ;

        public myViewHolder2(@NonNull View itemView) {
            super(itemView);

            read_Kegiatan_dokumentasi = itemView.findViewById(R.id.read_Kegiatan_dokumentasi);
            read_imgKegiatan_Dokumentasi = itemView.findViewById(R.id.read_imgKegiatan_Dokumentasi);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
