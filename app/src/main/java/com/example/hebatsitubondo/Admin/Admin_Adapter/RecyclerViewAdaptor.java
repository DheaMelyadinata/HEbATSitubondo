package com.example.hebatsitubondo.Admin.Admin_Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.Admin.Detail_Portofolio;
import com.example.hebatsitubondo.Admin.Update_tema;
import com.example.hebatsitubondo.Orangtua.Model.TemaPortofolioTM;
import com.example.hebatsitubondo.Orangtua.Portofolio_Detail;
import com.example.hebatsitubondo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.MyViewHolder> {

    private Context mContext;
    private static List<TemaPortofolioTM> mData;
    private String url = " http://192.168.43.62/API_HEbATSitubondo/public/tema";

    public RecyclerViewAdaptor(Context mContext, List<TemaPortofolioTM> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_tema_portofolio, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final TemaPortofolioTM temaPortofolioTM = mData.get(position);
        holder.tv_tema.setText(mData.get(position).getNamaTema());
        holder.tv_judul_tema.setText(mData.get(position).getJudulTema());

        Glide.with(holder.itemView.getContext())
                .load("http://192.168.43.62/API_HEbATSitubondo/public/" + temaPortofolioTM.getGambar_url())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.img_thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), Detail_Portofolio.class);
                i.putExtra("nama_tema", temaPortofolioTM.getNamaTema());
                i.putExtra("judul_tema", temaPortofolioTM.getJudulTema());
                i.putExtra("tujuan", temaPortofolioTM.getTujuan());
                i.putExtra("kegiatan", temaPortofolioTM.getKegiatanTema());
                i.putExtra("catatan", temaPortofolioTM.getCatatan());
                i.putExtra("gambar_tema", temaPortofolioTM.getGambar_url());

                holder.itemView.getContext().startActivity(i);
            }
        });

        holder.editTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.editTema.getContext(), Update_tema.class);
                i.putExtra("id_tema", temaPortofolioTM.getIdTema());
                i.putExtra("nama_tema", temaPortofolioTM.getNamaTema());
                i.putExtra("judul_tema", temaPortofolioTM.getJudulTema());
                i.putExtra("tujuan", temaPortofolioTM.getTujuan());
                i.putExtra("kegiatan", temaPortofolioTM.getKegiatanTema());
                i.putExtra("catatan", temaPortofolioTM.getCatatan());
                i.putExtra("gambar_tema", temaPortofolioTM.getGambar_url());

                holder.editTema.getContext().startActivity(i);
            }
        });

        holder.hapusTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(holder.hapusTema.getContext());
                alertDialogBuilder
                        .setMessage("Apakah Anda yakin ingin menghapus data tema ?")
                        .setCancelable(false)
                        .setPositiveButton("Ya",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        holder.delete(temaPortofolioTM.getIdTema(),temaPortofolioTM);
                                    }
                                })
                        .setNegativeButton("Tidak",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertDialogBuilder.setCancelable(true);
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_tema;
        TextView tv_judul_tema;
        ImageView img_thumbnail;
        ImageButton hapusTema;
        ImageButton editTema;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_tema = itemView.findViewById(R.id.tvTema);
            tv_judul_tema = itemView.findViewById(R.id.tvdeskripsiTema);
            img_thumbnail = itemView.findViewById(R.id.imgTema);
            hapusTema = itemView.findViewById(R.id.deleteTema);
            editTema = itemView.findViewById(R.id.editTema);
        }

//Delete (Menghapus) Tema

        private void delete(Integer idTema, final TemaPortofolioTM temaPortofolioTM) {
            RequestQueue queue = Volley.newRequestQueue(itemView.getContext());
            String url_delete = " http://192.168.43.62/API_HEbATSitubondo/public/tema/" + idTema;

            final StringRequest request = new StringRequest(Request.Method.DELETE, url_delete, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    notifyItemRemoved(getAdapterPosition());
                    mData.remove(temaPortofolioTM);
                    Toast.makeText(itemView.getContext(), "Berhasil Hapus Tema", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(itemView.getContext(), error.toString(), Toast.LENGTH_LONG).show();

                }
            });

            queue.getCache().clear();
            queue.add(request);
        }
    }

}



