package com.example.sonkt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AddUrun extends AppCompatActivity {
    Button scanBtn, kaydet;
    private EditText txtUrunAdi,txtUrunFiyati,txtUrunGrami,txtUrunSKT,txtUrunKodu;
    private String urunAdi,urunFiyati,urunGrami,urunSKT,urunKodu;


    private void init(){
        txtUrunAdi=findViewById(R.id.add_urun_activity_urunAdi);
        txtUrunFiyati=findViewById(R.id.add_urun_activity_urunFiyati);
        txtUrunGrami=findViewById(R.id.add_urun_activity_urunGram);
        txtUrunSKT=findViewById(R.id.add_urun_activity_urunSonKT);
        txtUrunKodu=findViewById(R.id.add_urun_activity_urunKodu);
        kaydet=findViewById(R.id.add_urun_activity_kaydet);
        scanBtn=findViewById(R.id.scanBtn);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_urun);
        init();

    }

    public void urunKaydet(View v){
        urunAdi=txtUrunAdi.getText().toString();
        urunGrami=txtUrunGrami.getText().toString();
        urunFiyati=txtUrunFiyati.getText().toString();
        urunSKT=txtUrunSKT.getText().toString();
        //urunkodu string halde
        if (!TextUtils.isEmpty(urunAdi)) {
                if (!TextUtils.isEmpty(urunSKT)) {
                    //Kaydet
                    try{
                        SQLiteDatabase database =this.openOrCreateDatabase("Urunler",MODE_PRIVATE,null);
                        database.execSQL("CREATE TABLE IF NOT EXISTS urunler(id INTEGER PRIMARY KEY, urunAdi VARCHAR, urunFiyati VARCHAR, urunSKT VARCHAR, urunGrami VARCHAR,urunKodu VARCHAR)");

                        String sqlSorgusu= "INSERT INTO urunler (urunAdi,urunFiyati,urunSKT,urunGrami,urunKodu) VALUES (?,?,?,?,?)";
                        SQLiteStatement statement=database.compileStatement(sqlSorgusu);
                        statement.bindString(1,urunAdi);
                        statement.bindString(2,urunFiyati);
                        statement.bindString(3,urunSKT);
                        statement.bindString(4,urunGrami);
                        statement.bindString(5,urunKodu);
                        statement.execute();//verileri ekledi
                        nesneleriTemizle();
                        showToast("Kayıt Başarıyla eklendi");
                        kaydet.setEnabled(true);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else
                    showToast("Son Kullanma Tarihi Boş Olamaz");
                }else
            showToast("Urun Adi Bos Olamaz");
        }
    private void showToast(String mesaj) {
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
    }
    private void nesneleriTemizle(){
        txtUrunAdi.setText("");
        txtUrunGrami.setText("");
        txtUrunSKT.setText("");
        txtUrunFiyati.setText("");
        txtUrunKodu.setText("");
        kaydet.setEnabled(false);
    }

    public void barkodOkuma(View v) {
        scanCode();

    }

    private void scanCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scann Code");
        integrator.initiateScan();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents() !=null){
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());

                builder.setTitle("scanning");
                builder.setPositiveButton("Numarayı ekrana yazdır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        urunKodu= result.getContents();
                        txtUrunKodu.setText(urunKodu);

                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();

            }
            else{
                Toast.makeText(this,"No result",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }

    }



}