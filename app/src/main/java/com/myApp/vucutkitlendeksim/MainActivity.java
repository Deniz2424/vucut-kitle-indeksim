package com.myApp.vucutkitlendeksim;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.Integer;
import java.lang.String;



public class MainActivity extends AppCompatActivity {
    SeekBar seekBarvki;
    TextView textViewvki,textViewidealkilo,textViewboy,textViewkilo,textviewdurum;
    RadioButton rberkek,rbkadin;
    RadioGroup radioGroup2;
    EditText edittextkilo,edittextboy,edittextyas;
    private Button btnhesapla,btngoster;
    int boy;
    double kilo,doublevki;
    int yas;
     BigDecimal vki;
     double idealerkek,idealkadin;

    public MainActivity() {

    }
    public void durumgoster(){
        if (edittextkilo.getText().toString().matches("")||edittextboy.getText().toString().matches("")||edittextyas.getText().toString().matches("")) {
            Toast toast = Toast.makeText(MainActivity.this,"Lütfen boşlukları dolduralım", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else {
            String durumbilgi=textviewdurum.getText().toString();
            int  color=textviewdurum.getCurrentTextColor();
            Intent gecis = new Intent(MainActivity.this, DetailsActivity.class );
            gecis.putExtra("durum",durumbilgi);
            gecis.putExtra("renk",color);
            gecis.putExtra("doublevki",doublevki);
            startActivity(gecis);
        }
        }

    public void hesaplamalar(){
        if (edittextkilo.getText().toString().matches("")||edittextboy.getText().toString().matches("")||edittextyas.getText().toString().matches("")) {
            Toast toast = Toast.makeText(MainActivity.this,"Lütfen boşlukları dolduralım", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            }
        else{
            vkihesapla(kilo, boy);
                idealhesapla(boy);
                seekBarvki.setProgress(vki.intValue());
                vkidurumgoster();
        }
    }
     //Vucüt kitle indeksinin hesaplamasının yapıldığı kısım
    public void vkihesapla( double a, int b) {

        BigDecimal divisor = new BigDecimal("100");

            vki = BigDecimal.valueOf(a).divide((BigDecimal.valueOf(b).divide(divisor, 2, RoundingMode.HALF_UP)).pow(2), 2, RoundingMode.HALF_UP);
            textViewvki.setText(String.format("Vücut Kitle indeksiniz :%s", vki));
    }
    //ideal kilonunu hesaplandığı kısım
    public void idealhesapla(int x){

        idealkadin= 45.5+((2.3/2.54)*(x-152.4));
        idealerkek= 50+((2.3/2.54)*(x-152.4));
        if(rbkadin.isChecked()) {
            textViewidealkilo.setText(String.format("İdeal Kilonuz : %s", (int) idealkadin));
        }
        if (rberkek.isChecked()){
            textViewidealkilo.setText(String.format("İdeal Kilonuz : %s",  (int)idealerkek));
        }

    }

    public void vkidurumgoster(){
        doublevki=Double.parseDouble(String.valueOf(vki));
      if(18.5 > doublevki){
          textviewdurum.setText(R.string.Zayıf);
           textviewdurum.setTextColor(Color.parseColor("#0D81ED"));
          textViewvki.setTextColor(Color.parseColor("#0D81ED"));
      }
      else if (20>doublevki  &&  doublevki>=18.5){
          textviewdurum.setText(R.string.Hafifzayıf);
          textviewdurum.setTextColor(Color.parseColor("#1FB9EE"));
          textViewvki.setTextColor(Color.parseColor("#1FB9EE"));
      }
      else if (25>doublevki && doublevki>=20){
          textviewdurum.setText(R.string.Normal);
          textviewdurum.setTextColor(Color.parseColor("#09ED10"));
          textViewvki.setTextColor(Color.parseColor("#09ED10"));
      }

      else if (30>doublevki && doublevki>=25){
          textviewdurum.setText(R.string.Hafifkilolu);
          textviewdurum.setTextColor(Color.parseColor("#AFE70D"));
          textViewvki.setTextColor(Color.parseColor("#AFE70D"));

      }
      else if (35>doublevki && doublevki>=30){
          textviewdurum.setText(R.string.Kilolu);
          textviewdurum.setTextColor(Color.parseColor("#ED6D09"));
          textViewvki.setTextColor(Color.parseColor("#ED6D09"));

      }
      else if (40>=doublevki && doublevki>=35){
          textviewdurum.setText(R.string.Asırıkilolu);
          textviewdurum.setTextColor(Color.parseColor("#FF0000"));
          textViewvki.setTextColor(Color.parseColor("#FF0000"));
      }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarvki= findViewById(R.id.seekBarvki);
        textViewkilo= findViewById(R.id.textViewkilo);
        textViewboy=findViewById(R.id.textViewboy);
        textViewidealkilo= findViewById(R.id.textViewidealkilo);
        textViewvki=findViewById(R.id.textViewvki);
        btnhesapla=findViewById(R.id.btnhesapla);
        btngoster=findViewById(R.id.btndetay);
        rberkek=findViewById(R.id.rberkek);
        rbkadin= findViewById(R.id.rbkadin);
        radioGroup2=findViewById(R.id.radioGroup2);
        edittextkilo=findViewById(R.id.edittextkilo);
        edittextboy=findViewById(R.id.edittextboy);
        edittextyas=findViewById(R.id.edittextyas);
        textviewdurum=findViewById(R.id.textviewdurum);

        addListenerOnButton();

        edittextkilo.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

       @Override
       public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                if(s!=""){
                    kilo=Double.parseDouble(String.valueOf(s));
                }

            }
            catch (Exception ignored){

            }
       }

       @Override
       public void afterTextChanged(Editable s) {

       }
   });
       edittextboy.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               try {
                   if(s!=""){
                       boy=Integer.parseInt(String.valueOf(s));
                   }


               }
               catch (Exception ignored){
               }

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

      edittextyas.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              try {
                  if(s!=""){
                      yas=Integer.parseInt(String.valueOf(s));

                  }

              }
              catch (Exception ignored){

              }
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });

    }
    public void addListenerOnButton() {

        btnhesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               hesaplamalar();
            }

        });
        btngoster.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                durumgoster();
            }
        });

    }
}