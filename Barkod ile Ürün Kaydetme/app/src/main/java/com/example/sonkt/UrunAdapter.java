package com.example.sonkt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UrunAdapter extends RecyclerView.Adapter<UrunAdapter.UrunHolder> {
    private ArrayList<Urunler> urunList;
    private Context context;

    public UrunAdapter(ArrayList<Urunler> urunList, Context context) {
        this.urunList = urunList;
        this.context = context;
    }

    @NonNull
    @Override
    public UrunHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.urun_item,parent,false);
        return new UrunHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UrunHolder holder, int position) {
        Urunler urun=urunList.get(position);
        holder.setData(urun);

    }

    @Override
    public int getItemCount() {
        return urunList.size();
    }

    class UrunHolder extends RecyclerView.ViewHolder{
        TextView txtUrunAdi,txtUrunFiyati,txtUrunGrami,txtUrunSKT,txtUrunKodu;

        public UrunHolder(@NonNull View itemView) {
            super(itemView);
            txtUrunAdi=itemView.findViewById(R.id.urun_item_textViewUrunAdi);
            txtUrunFiyati=itemView.findViewById(R.id.urun_item_textViewUrunFiyati);
            txtUrunGrami=itemView.findViewById(R.id.urun_item_textViewUrunGrami);
            txtUrunSKT=itemView.findViewById(R.id.urun_item_textViewUrunSKT);
            txtUrunKodu=itemView.findViewById(R.id.urun_item_textViewUrunKodu);

        }

        public void setData(Urunler urun){
            this.txtUrunAdi.setText("Ürün Adı: "+ urun.getUrunAdi());
            this.txtUrunFiyati.setText("Ürün Fiyatı: "+ urun.getUrunFiyati());
            this.txtUrunGrami.setText("Ürün Gramı: " + urun.getUrunGrami());
            this.txtUrunKodu.setText("Ürün Kodu: " + urun.getUrunKodu());
            this.txtUrunSKT.setText("Ürün Son Kullanma Tarihi: "+ urun.getUrunSKT());
        }
    }
}

