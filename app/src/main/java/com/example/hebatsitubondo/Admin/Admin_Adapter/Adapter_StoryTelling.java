package com.example.hebatsitubondo.Admin.Admin_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hebatsitubondo.Admin.Detail_Portofolio;
import com.example.hebatsitubondo.Admin.Detail_Story_Telling;
import com.example.hebatsitubondo.Admin.Model_Admin.Model_StoryTelling;
import com.example.hebatsitubondo.R;

import java.util.List;

public class Adapter_StoryTelling extends RecyclerView.Adapter<Adapter_StoryTelling.MyViewHolder> {
    private Context context;
    private List<Model_StoryTelling> data;
    private String url_storytelling = " http://192.168.43.62/API_HEbATSitubondo/public/story_telling ";

    public Adapter_StoryTelling(Context context, List<Model_StoryTelling> data ) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_story_telling,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Model_StoryTelling model_storyTelling = data.get(position);
        holder.TemaStory.setText(data.get(position).getNama_tema());
        holder.Nama_Anak.setText(data.get(position).getNama_anak());
        holder.Judul_Tema_Story.setText(data.get(position).getJudul_tema());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), Detail_Story_Telling.class);
                i.putExtra("nama_tema", model_storyTelling.getNama_tema());
                i.putExtra("nama_anak", model_storyTelling.getNama_anak());
                i.putExtra("judul_tema", model_storyTelling.getJudul_tema());
                i.putExtra("cerita", model_storyTelling.getCerita());
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView TemaStory;
        TextView Nama_Anak;
        TextView Judul_Tema_Story;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TemaStory = itemView.findViewById(R.id.TemaStory);
            Nama_Anak = itemView.findViewById(R.id.Nama_Anak);
            Judul_Tema_Story = itemView.findViewById(R.id.Judul_Tema_Story);
        }
    }
}
