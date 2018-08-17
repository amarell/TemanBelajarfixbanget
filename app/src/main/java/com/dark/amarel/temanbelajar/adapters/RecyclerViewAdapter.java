package com.dark.amarel.temanbelajar.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dark.amarel.temanbelajar.models.DataGuru;
import com.dark.amarel.temanbelajar.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<DataGuru> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<DataGuru> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option=new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater= LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.guru_row_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tv_nama_guru.setText(mData.get(position).getNama());
        holder.tv_pendidikan.setText(mData.get(position).getPendidikan());
        holder.tv_mapel.setText(mData.get(position).getNama_mapel());
        holder.tv_deskripsi.setText(mData.get(position).getDeskripsi());
        holder.tv_pengalaman.setText(mData.get(position).getPengalaman());

        Glide.with(mContext).load(mData.get(position).getFoto_profil()).apply(option).into(holder.fotoguru);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_nama_guru, tv_pendidikan, tv_mapel, tv_deskripsi, tv_pengalaman;
        ImageView fotoguru;


        public MyViewHolder(View itemView) {
            super(itemView);

            tv_nama_guru=itemView.findViewById(R.id.rowname);
            tv_pendidikan=itemView.findViewById(R.id.pendidikan);
            tv_mapel=itemView.findViewById(R.id.namamapel);
            tv_deskripsi=itemView.findViewById(R.id.deskripsi);
            tv_pengalaman=itemView.findViewById(R.id.pengalaman);
            fotoguru=itemView.findViewById(R.id.thumbnail);
        }
    }
}
