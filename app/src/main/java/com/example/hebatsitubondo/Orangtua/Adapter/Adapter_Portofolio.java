package com.example.hebatsitubondo.Orangtua.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.Orangtua.Model.TemaPortofolioTM;
import com.example.hebatsitubondo.Orangtua.Portofolio_Anak;
import com.example.hebatsitubondo.Orangtua.Portofolio_Detail;
import com.example.hebatsitubondo.R;

import java.util.List;

public class Adapter_Portofolio extends RecyclerView.Adapter<Adapter_Portofolio.myViewHolder4> {

    private Context mContext;
    private List<TemaPortofolioTM> mData;
    private String url = " http://192.168.43.62/API_HEbATSitubondo/public/tema";


    public Adapter_Portofolio(Context mContext, List<TemaPortofolioTM> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_tema_portofolio_read,parent,false);
        return new myViewHolder4(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder4 holder, int position) {

        final TemaPortofolioTM temaPortofolioTM = mData.get(position);
        holder.tvTema_read.setText(mData.get(position).getNamaTema());
        holder.tvdeskripsiTema.setText(mData.get(position).getJudulTema());

        Glide.with(holder.itemView.getContext())
                .load("http://192.168.43.62/API_HEbATSitubondo/public/" + temaPortofolioTM.getGambar_url())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgTema_read);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(holder.itemView.getContext(), Portofolio_Detail.class);
                i.putExtra("nama_tema", temaPortofolioTM.getNamaTema());
                i.putExtra("judul_tema", temaPortofolioTM.getJudulTema());
                i.putExtra("tujuan", temaPortofolioTM.getTujuan());
                i.putExtra("kegiatan", temaPortofolioTM.getKegiatanTema());
                i.putExtra("catatan", temaPortofolioTM.getCatatan());
                i.putExtra("gambar_tema", temaPortofolioTM.getGambar_url());

                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder4 extends RecyclerView.ViewHolder {

        TextView tvTema_read;
        TextView tvdeskripsiTema;
        ImageView imgTema_read;


        public myViewHolder4(@NonNull View itemView) {
            super(itemView);

            tvTema_read = itemView.findViewById(R.id.tvTema_read);
            tvdeskripsiTema = itemView.findViewById(R.id.tvdeskripsiTema);
            imgTema_read = itemView.findViewById(R.id.imgTema_read);

        }
    }
}
