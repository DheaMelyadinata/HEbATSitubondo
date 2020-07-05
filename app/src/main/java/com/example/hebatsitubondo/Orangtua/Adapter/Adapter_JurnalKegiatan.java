package com.example.hebatsitubondo.Orangtua.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.Orangtua.Model.TemaPortofolioTM;
import com.example.hebatsitubondo.Orangtua.Portofolio_Anak;
import com.example.hebatsitubondo.R;

import java.util.List;

public class Adapter_JurnalKegiatan extends RecyclerView.Adapter<Adapter_JurnalKegiatan.myViewHolder3> {

    private Context mContext;
    private List<TemaPortofolioTM> mData;

    public Adapter_JurnalKegiatan(Context mContext, List<TemaPortofolioTM> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_jurnal_kegiatan,parent,false);
        return new myViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder3 holder, int position) {

        final TemaPortofolioTM temaPortofolioTM = mData.get(position);

        holder.tv_jurnal_tema.setText(mData.get(position).getNamaTema());
        holder.tv_jurnal_judul_tema.setText(mData.get(position).getJudulTema());

        Glide.with(holder.itemView.getContext())
                .load("http://192.168.43.62/API_HEbATSitubondo/public/"+ temaPortofolioTM.getGambar_url())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.jurnal_img_thumbnail);

        holder.detail_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(holder.itemView.getContext(), Portofolio_Anak.class);
                i.putExtra("nama_tema",temaPortofolioTM.getNamaTema());
                i.putExtra("judul_tema",temaPortofolioTM.getJudulTema());
                i.putExtra("gambar_tema",temaPortofolioTM.getGambar_url());

                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder3 extends RecyclerView.ViewHolder {

        TextView tv_jurnal_tema;
        TextView tv_jurnal_judul_tema;
        ImageView jurnal_img_thumbnail;
        Button detail_data;

        public myViewHolder3(@NonNull View itemView) {
            super(itemView);

            tv_jurnal_tema = itemView.findViewById(R.id.tvTema);
            tv_jurnal_judul_tema = itemView.findViewById(R.id.tvdeskripsiTema);
            jurnal_img_thumbnail = itemView.findViewById(R.id.imgTema);
            detail_data = itemView.findViewById(R.id.btn_set_data);
        }
    }
}
