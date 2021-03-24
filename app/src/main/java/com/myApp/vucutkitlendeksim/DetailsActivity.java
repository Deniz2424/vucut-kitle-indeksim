package com.myApp.vucutkitlendeksim;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class DetailsActivity extends AppCompatActivity {
    TextView twdurumbilgi;
    GraphView graph;
    String StringVki;

   public void veriyaz(){
       @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    Database veritabani= new Database(DetailsActivity.this);
       veritabani.veriekle(StringVki,dateFormat.format(date));
}
  public void listeekle(){
       Database veritabani =new Database( DetailsActivity.this);
       LinkedList<String> degerlerim = veritabani.veriListeleri();

  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        twdurumbilgi=findViewById(R.id.twdurumbilgi);
        graph =findViewById(R.id.graph);
        Intent gecis= getIntent();
        String vkidurum2 = gecis.getStringExtra("durum");
        int textrengi= gecis.getIntExtra("renk",0);
        double doublevki= gecis.getDoubleExtra("doublevki",0.0);
        twdurumbilgi.setText(vkidurum2);
        twdurumbilgi.setTextColor(textrengi);
        StringVki=String.valueOf(doublevki);
         veriyaz();
    }
}