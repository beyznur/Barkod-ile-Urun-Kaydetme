package com.example.sonkt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Urunler {
    private String urunAdi,urunGrami,urunFiyati,urunSKT,urunKodu;


 public Urunler(){}

    public Urunler(String urunAdi, String urunGrami, String urunFiyati, String urunSKT, String urunKodu) {
        this.urunAdi = urunAdi;
        this.urunGrami = urunGrami;
        this.urunFiyati = urunFiyati;
        this.urunSKT = urunSKT;
        this.urunKodu = urunKodu;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String getUrunGrami() {
        return urunGrami;
    }

    public void setUrunGrami(String urunGrami) {
        this.urunGrami = urunGrami;
    }

    public String getUrunFiyati() {
        return urunFiyati;
    }

    public void setUrunFiyati(String urunFiyati) {
        this.urunFiyati = urunFiyati;
    }

    public String getUrunSKT() {
        return urunSKT;
    }

    public void setUrunSKT(String urunSKT) {
        this.urunSKT = urunSKT;
    }

    public String getUrunKodu() {
        return urunKodu;
    }

    public void setUrunKodu(String urunKodu) {
        this.urunKodu = urunKodu;
    }
 static public ArrayList<Urunler> getData(Context context) {
        ArrayList<Urunler> urunList = new ArrayList<>();
        ArrayList<String> urunAdiList = new ArrayList<>();
        ArrayList<String> urunSKTList = new ArrayList<>();
        ArrayList<String> urunGramiList = new ArrayList<>();
        ArrayList<String> urunFiyatiList = new ArrayList<>();
        ArrayList<String> urunKoduList = new ArrayList<>();

        try{
            SQLiteDatabase database=context.openOrCreateDatabase("Urunler",Context.MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM urunler",null);
            int urunAdiIndex=cursor.getColumnIndex("urunAdi");
            int urunFiyatiIndex=cursor.getColumnIndex("urunFiyati");
            int urunSKTIndex=cursor.getColumnIndex("urunSKT");
            int urunGramiIndex=cursor.getColumnIndex("urunGrami");
            int urunKoduIndex=cursor.getColumnIndex("urunKodu");

            while (cursor.moveToNext()){
                urunAdiList.add(cursor.getString(urunAdiIndex));
                urunFiyatiList.add(cursor.getString(urunFiyatiIndex));
                urunSKTList.add(cursor.getString(urunSKTIndex));
                urunGramiList.add(cursor.getString(urunGramiIndex));
                urunKoduList.add(cursor.getString(urunKoduIndex));
            }
            cursor.close();

            for(int i=0; i<urunAdiList.size();i++){
                Urunler urun=new Urunler();
                urun.setUrunAdi(urunAdiList.get(i));
                urun.setUrunFiyati(urunFiyatiList.get(i));
                urun.setUrunSKT(urunSKTList.get(i));
                urun.setUrunGrami(urunGramiList.get(i));
                urun.setUrunKodu(urunKoduList.get(i));

                urunList.add(urun);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     return urunList;

 }
}
