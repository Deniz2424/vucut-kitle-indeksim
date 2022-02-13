package com.myApp.vucutkitlendeksim;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;



public class DetailsActivity extends AppCompatActivity {
    TextView twdurumbilgi,textView2;
    String StringVki,secili,secili2;
    ListView listview;
   private Button btnlist,button;


    public void veriyaz(){
       Database veritabani= new Database(DetailsActivity.this);
       @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Date date = new Date(System.currentTimeMillis());
       veritabani.veriekle(StringVki,dateFormat.format(date));
}
  public void listele(){
      Database veritabani = new Database(DetailsActivity.this);
     List<String> data = veritabani.veriListeleri();
      Collections.reverse(data);
      ArrayAdapter<String> adapter= new ArrayAdapter<>(DetailsActivity.this, android.R.layout.activity_list_item, android.R.id.text1, data);
      listview.setAdapter(adapter);


  }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        twdurumbilgi=findViewById(R.id.twdurumbilgi);
        Intent gecis= getIntent();
        String vkidurum2 = gecis.getStringExtra("durum");
        int textrengi= gecis.getIntExtra("renk",0);
        double doublevki= gecis.getDoubleExtra("doublevki",0.0);
        twdurumbilgi.setText(vkidurum2);
        twdurumbilgi.setTextColor(textrengi);
        StringVki=String.valueOf(doublevki);
        btnlist= findViewById(R.id.btnlist);
        listview =findViewById(R.id.listview);
        button=findViewById(R.id.button);
        textView2=findViewById(R.id.textView2);
         veriyaz();
        TextView view = new TextView(getApplication());
        view.setText("Vücut Kitle İndeksi    Tarih");
        listview.addHeaderView(view);
        listele();


         listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
             @Override
             public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                 if (String.valueOf(view.getBackground()).equals(String.valueOf(Color.parseColor("#8BC34A")))) {
                     view.setBackgroundColor(Color.parseColor("#EAE8E7"));
                 }
                 else {
                     view.setBackgroundColor(Color.parseColor("#8BC34A"));
                 }
                 secili = listview.getItemAtPosition(position).toString();
                 int a = secili.indexOf(' ');
                 secili2 = secili.substring(0, a);
                 textView2.setText(secili2);
                 return false;
             }
         });


         addListenerOnButton();
    }

    public void addListenerOnButton(){
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Database veritabani = new Database(DetailsActivity.this);
                veritabani.verisil(secili2);
                listele();
           }
       });
       btnlist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //listele();

           }
       });
    }
}