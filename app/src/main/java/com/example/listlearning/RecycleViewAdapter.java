package com.example.listlearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class RecycleViewAdapter extends  RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Mahasiswa> albumList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
        TextView nama,nim,kejuruan,alamat;
        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.image);
            nama = v.findViewById(R.id.tvNama);
            nim = v.findViewById(R.id.tvNim);
            kejuruan = v.findViewById(R.id.tvKejuruan);
            alamat = v.findViewById(R.id.tvAlamat);
        }

    }

    public RecycleViewAdapter(Context mContext, List<Mahasiswa> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mahasiswa, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Mahasiswa album = albumList.get(position);
        holder.nama.setText(album.getNama());
        holder.nim.setText(album.getNim());
        holder.kejuruan.setText(album.getKejuruan());
        holder.alamat.setText(album.getAlamat());
        Glide.with(mContext)
                .load(new File(album.getGambar()))
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
