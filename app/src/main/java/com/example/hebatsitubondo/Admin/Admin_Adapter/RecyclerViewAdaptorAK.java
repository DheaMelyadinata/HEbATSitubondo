package com.example.hebatsitubondo.Admin.Admin_Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Dokumentasi_Kegiatan;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_Kegiatan;
import com.example.hebatsitubondo.Admin.Update_kegiatan;
import com.example.hebatsitubondo.Orangtua.AgendaKegiatan_Detail;
import com.example.hebatsitubondo.R;

import java.util.List;

public class RecyclerViewAdaptorAK extends RecyclerView.Adapter<RecyclerViewAdaptorAK.MyViewHolderAK> {

    private Context mContext;
    private static List<Model_Kegiatan> mData;

    public RecyclerViewAdaptorAK(Context mContext, List<Model_Kegiatan> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolderAK onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_agenda_kegiatan, parent, false);
        return new MyViewHolderAK(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolderAK holder, final int position) {

        final Model_Kegiatan modelKegiatan = mData.get(position);

        holder.tvJenis_Kegiatan.setText(mData.get(position).getJenisKegiatan());
        holder.tvNama_Kegiatan.setText(mData.get(position).getNamaKegiatan());
        holder.tvWaktu_Kegiatan.setText(mData.get(position).getWaktu_kegiatan());
        holder.tvTanggal_Kegiatan.setText(mData.get(position).getTanggal_pelaksanaan());
        holder.tvTempat.setText(mData.get(position).getTempat());

        Glide.with(holder.itemView.getContext())
                .load("http://192.168.43.62/API_HEbATSitubondo/public/" + modelKegiatan.getPhotoKegiatan())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgKegiatan);

        holder.btn_set_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(holder.itemView.getContext(), Update_kegiatan.class);
                i.putExtra("id_kegiatan", modelKegiatan.getIdKegiatan());
                i.putExtra("jenis_kegiatan", modelKegiatan.getJenisKegiatan());
                i.putExtra("nama_kegiatan", modelKegiatan.getNamaKegiatan());
                i.putExtra("waktu_kegiatan", modelKegiatan.getWaktu_kegiatan());
                i.putExtra("tanggal_pelaksanaan", modelKegiatan.getTanggal_pelaksanaan());
                i.putExtra("tempat", modelKegiatan.getTempat());
                i.putExtra("photo_kegiatan", modelKegiatan.getPhotoKegiatan());

                holder.itemView.getContext().startActivity(i);
            }
        });

        holder.btn_set_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(holder.btn_set_delete.getContext());
                alertDialogBuilder
                        .setMessage("Apakah Anda yakin ingin menghapus data kegiatan ?")
                        .setCancelable(false)
                        .setPositiveButton("Ya",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        holder.delete(modelKegiatan.getIdKegiatan(),modelKegiatan);
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

    public class MyViewHolderAK extends RecyclerView.ViewHolder {

        TextView tvJenis_Kegiatan;
        TextView tvNama_Kegiatan;
        TextView tvWaktu_Kegiatan;
        TextView tvTanggal_Kegiatan;
        TextView tvTempat;
        ImageView imgKegiatan;
        Button btn_set_update;
        Button btn_set_delete;


        public MyViewHolderAK(@NonNull View itemView) {
            super(itemView);

            tvJenis_Kegiatan = itemView.findViewById(R.id.tvJenis_Kegiatan);
            tvNama_Kegiatan = itemView.findViewById(R.id.tvNama_Kegiatan);
            tvWaktu_Kegiatan = itemView.findViewById(R.id.tvWaktu_Kegiatan);
            tvTanggal_Kegiatan = itemView.findViewById(R.id.tvTanggal_Kegiatan);
            tvTempat = itemView.findViewById(R.id.tvTempat);
            imgKegiatan = itemView.findViewById(R.id.imgKegiatan);
            btn_set_update = itemView.findViewById(R.id.btn_set_update);
            btn_set_delete = itemView.findViewById(R.id.btn_set_delete);
        }

//        Delete (Menghapus) Agenda Kegiatan

        private void delete(Integer id_Kegiatan, final Model_Kegiatan modelKegiatan) {
            RequestQueue queue = Volley.newRequestQueue(itemView.getContext());
            String url_delete = "http://192.168.43.62/API_HEbATSitubondo/public/kegiatan/" + id_Kegiatan;

            final StringRequest request = new StringRequest(Request.Method.DELETE, url_delete, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    notifyItemRemoved(getAdapterPosition());
                    mData.remove(modelKegiatan);
                    Toast.makeText(itemView.getContext(), "Berhasil Hapus Agenda Kegiatan", Toast.LENGTH_SHORT).show();
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

