package com.dark.amarel.temanbelajar.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dark.amarel.temanbelajar.CustomOnItemClickListener;
import com.dark.amarel.temanbelajar.R;
import com.dark.amarel.temanbelajar.detail_transaksi;
import com.dark.amarel.temanbelajar.models.DataListTransaksi;

import java.util.List;

public class ListTransaksiAdapter extends RecyclerView.Adapter<ListTransaksiAdapter.MyViewHolder> {

    private Context mContext;
    private List<DataListTransaksi> mData;
    RequestOptions option;

    public ListTransaksiAdapter(Context mContext, List<DataListTransaksi> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option=new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.transaksi_row_item, parent, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.containertransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, detail_transaksi.class );
                intent.putExtra("namaguru", mData.get(myViewHolder.getAdapterPosition()).getNama_tentor());
                intent.putExtra("statusbayar", mData.get(myViewHolder.getAdapterPosition()).getStatus_bayar());
                intent.putExtra("statusles", mData.get(myViewHolder.getAdapterPosition()).getStatus_les());
                intent.putExtra("statusbooking", mData.get(myViewHolder.getAdapterPosition()).getStatus_booking());
                intent.putExtra("hari", mData.get(myViewHolder.getAdapterPosition()).getHari_les());
                intent.putExtra("jam", mData.get(myViewHolder.getAdapterPosition()).getJam_les());
                intent.putExtra("jumlahpertemuan", mData.get(myViewHolder.getAdapterPosition()).getJumlah_pertemuan());
                intent.putExtra("foto_guru", mData.get(myViewHolder.getAdapterPosition()).getFoto_profil());
                mContext.startActivity(intent);
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nama.setText(mData.get(position).getNama_tentor());
        holder.mapel.setText(mData.get(position).getNama_mapel());
        holder.status.setText(mData.get(position).getStatus_booking());
        Glide.with(mContext).load(mData.get(position).getFoto_profil()).apply(option).into(holder.imageView);

        int panjang=mData.get(position).getTelpon_tentor().length();

        String apiwa="https://api.whatsapp.com/send?phone=";
        final String url = apiwa+"+62"+mData.get(position).getTelpon_tentor().substring(1, panjang);
        final String telpon="+62"+mData.get(position).getTelpon_tentor().substring(1, panjang);
        holder.chat.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        }));

        holder.telpon.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telpon, null));
                mContext.startActivity(intent);

            }
        }));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama, mapel, status;
        ImageView imageView;
        Button chat, telpon;
        LinearLayout containertransaksi;
        public MyViewHolder(View itemView) {
            super(itemView);

            containertransaksi=itemView.findViewById(R.id.containertransaksi);
            nama=itemView.findViewById(R.id.rowname);
            mapel=itemView.findViewById(R.id.namamapel);
            status=itemView.findViewById(R.id.status_booking);
            imageView=itemView.findViewById(R.id.img_item);
            chat=itemView.findViewById(R.id.btn_set_chat);
            telpon=itemView.findViewById(R.id.btn_set_telpon);



        }
    }
}
