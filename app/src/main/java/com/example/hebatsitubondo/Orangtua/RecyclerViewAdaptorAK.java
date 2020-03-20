package com.example.hebatsitubondo.Orangtua;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hebatsitubondo.R;

import java.util.List;

public class RecyclerViewAdaptorAK extends RecyclerView.Adapter<RecyclerViewAdaptorAK.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView judulKegiatan;
        ImageView fotoKegiatan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judulKegiatan=(TextView)itemView.findViewById(R.id.tvJudulKegiatan);
            fotoKegiatan=(ImageView) itemView.findViewById(R.id.imgKegiatan);
        }
    }

    public List<AgendaKegiatanTM> kegiatanList;

    public RecyclerViewAdaptorAK(List<AgendaKegiatanTM> kegiatanList) {
        this.kegiatanList = kegiatanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda_kegiatan, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.judulKegiatan.setText(kegiatanList.get(position).getJudulkegiatan());
        holder.fotoKegiatan.setImageResource(kegiatanList.get(position).getImgKegiatan());
    }

    @Override
    public int getItemCount() {
        return kegiatanList.size();
    }
}
