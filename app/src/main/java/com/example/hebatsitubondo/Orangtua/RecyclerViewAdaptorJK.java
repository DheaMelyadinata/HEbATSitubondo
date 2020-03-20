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

public class RecyclerViewAdaptorJK extends RecyclerView.Adapter<RecyclerViewAdaptorJK.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tema, deskripsiTema;
        ImageView fotoTema;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tema=(TextView)itemView.findViewById(R.id.tvTema);
            deskripsiTema=(TextView)itemView.findViewById(R.id.tvdeskripsiTema);
            fotoTema=(ImageView) itemView.findViewById(R.id.imgTema);
        }
    }

    public List<TemaPortofolioTM> temalist;

    public RecyclerViewAdaptorJK(List<TemaPortofolioTM> temalist) {
        this.temalist = temalist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jurnal_kegiatan, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tema.setText(temalist.get(position).getTema());
        holder.deskripsiTema.setText(temalist.get(position).getDeskripsiTema());
        holder.fotoTema.setImageResource(temalist.get(position).getImgTema());
    }

    @Override
    public int getItemCount() {
        return temalist.size();
    }
}
