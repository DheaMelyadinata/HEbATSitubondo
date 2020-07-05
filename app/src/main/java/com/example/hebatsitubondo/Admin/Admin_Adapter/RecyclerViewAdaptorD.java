package com.example.hebatsitubondo.Admin.Admin_Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Dokumentasi_Kegiatan;
import com.example.hebatsitubondo.Admin.Update_dokumentasi;
import com.example.hebatsitubondo.R;

import java.util.List;

public class RecyclerViewAdaptorD extends RecyclerView.Adapter<RecyclerViewAdaptorD.MyViewHolder_Dokumentasi> {

    private Context mContext;
    private static List<Model_Dokumentasi_Kegiatan> mData;

    public RecyclerViewAdaptorD(Context mContext, List<Model_Dokumentasi_Kegiatan> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder_Dokumentasi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_dokumentasi_kegiatan, parent, false);
        return new MyViewHolder_Dokumentasi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder_Dokumentasi holder, final int position) {

        final Model_Dokumentasi_Kegiatan modelDokumentasiKegiatan = mData.get(position);
        holder.tvKegiatan_dokumentasi.setText(mData.get(position).getNamaKegiatan());

        Glide.with(holder.itemView.getContext())
                .load("http://192.168.43.62/API_HEbATSitubondo/public/" + modelDokumentasiKegiatan.getPhotoDokumentasi())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgKegiatan_Dokumentasi);

        holder.btn_update_dokumentasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.btn_update_dokumentasi.getContext(), Update_dokumentasi.class);
                i.putExtra("id_dokumentasi_kegiatan", modelDokumentasiKegiatan.getId_Dokumentasi());
                i.putExtra("nama_kegiatan", modelDokumentasiKegiatan.getNamaKegiatan());
                i.putExtra("photo_dokumentasi", modelDokumentasiKegiatan.getPhotoDokumentasi());
                i.putExtra("deskripsi_kegiatan", modelDokumentasiKegiatan.getDeskripsiKegiatan());
                holder.btn_update_dokumentasi.getContext().startActivity(i);
            }
        });

        holder.btn_delete_dokumetasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(holder.btn_delete_dokumetasi.getContext());
                alertDialogBuilder
                        .setMessage("Apakah Anda yakin ingin menghapus data dokumentasi kegiatan ?")
                        .setCancelable(false)
                        .setPositiveButton("Ya",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        holder.delete(modelDokumentasiKegiatan.getId_Dokumentasi(),modelDokumentasiKegiatan);
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

    public class MyViewHolder_Dokumentasi extends RecyclerView.ViewHolder {

        TextView tvKegiatan_dokumentasi;
        ImageView imgKegiatan_Dokumentasi;
        Button btn_update_dokumentasi;
        Button btn_delete_dokumetasi;

        public MyViewHolder_Dokumentasi(@NonNull View itemView) {
            super(itemView);

            tvKegiatan_dokumentasi = itemView.findViewById(R.id.tvKegiatan_dokumentasi);
            imgKegiatan_Dokumentasi = itemView.findViewById(R.id.imgKegiatan_Dokumentasi);
            btn_update_dokumentasi = itemView.findViewById(R.id.btn_update_dokumentasi);
            btn_delete_dokumetasi = itemView.findViewById(R.id.btn_delete_dokumetasi);
        }

//        /Delete (Menghapus) Dokumentasi

        private void delete(Integer id_Dokumentasi, final Model_Dokumentasi_Kegiatan modelDokumentasiKegiatan) {
            RequestQueue queue = Volley.newRequestQueue(itemView.getContext());
            String url_delete = "http://192.168.43.62/API_HEbATSitubondo/public/dokumentasi_kegiatan/" + id_Dokumentasi;

            final StringRequest request = new StringRequest(Request.Method.DELETE, url_delete, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    notifyItemRemoved(getAdapterPosition());
                    mData.remove(modelDokumentasiKegiatan);
                    Toast.makeText(itemView.getContext(), "Berhasil Hapus Dokumentasi", Toast.LENGTH_SHORT).show();
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
