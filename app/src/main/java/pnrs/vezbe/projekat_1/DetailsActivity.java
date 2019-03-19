package pnrs.vezbe.projekat_1;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static pnrs.vezbe.projekat_1.R.string.prazan_string;

public class DetailsActivity<intent> extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {



    String celzijus ="Температура: 11";
    String farenhajt="Температура: 51.8";
    TextView ime_grada,dan;
    LinearLayout layout2,layout3,layout4;
    Button temp,sunce,vetar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String grad;

        if (getIntent().hasExtra("grad")) {
            grad = getIntent().getStringExtra("grad");
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + "grad");
        }

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = new Date();
        String filename = timeStampFormat.format(myDate);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE");
        String formattedDate = df.format(c.getTime());

        setContentView(R.layout.activity_details);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        ime_grada=(TextView) findViewById(R.id.TextView1);
        dan=(TextView) findViewById(R.id.textView4);
        ime_grada.setText(grad);
        //dan.setText("ДАН: " +formattedDate.toUpperCase());
        dan.setText(formattedDate.toUpperCase()+" "+filename);
        final TextView temperatura = (TextView) findViewById(R.id.textView7);
        String[] items = new String[]{"F", "C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String string = adapterView.getSelectedItem().toString();
               switch (string){
                   case "F":
                       temperatura.setText(farenhajt+" °F");
                       break;
                   case "C":
                       temperatura.setText(celzijus+" °C");
                       break;
                       default:
                           break;
               }
           }
           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {}

       });

        temp =(Button) findViewById(R.id.temperatura);
        sunce=(Button) findViewById(R.id.sunce);
        vetar =(Button) findViewById(R.id.vetar);

        layout2 =(LinearLayout) findViewById(R.id.Layout2);
        layout3 =(LinearLayout) findViewById(R.id.Layout3);
        layout4 =(LinearLayout) findViewById(R.id.Layout4);

        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.GONE);

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  temp.setBackgroundColor(Color.BLUE);
              // sunce.setBackgroundColor(Color.parseColor("#CAD1D3"));
                // vetar.setBackgroundColor(Color.parseColor("#CAD1D3"));
                temp.setTextColor(Color.BLACK);
                sunce.setTextColor(Color.WHITE);
                vetar.setTextColor(Color.WHITE);

                layout2.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.GONE);
                layout4.setVisibility(View.GONE);


            }
        });
        sunce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  temp.setBackgroundColor(Color.parseColor("#CAD1D3"));
              //  sunce.setBackgroundColor(Color.BLUE);
               // vetar.setBackgroundColor(Color.parseColor("#CAD1D3"));
                sunce.setTextColor(Color.BLACK);
                temp.setTextColor(Color.WHITE);
                vetar.setTextColor(Color.WHITE);

                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.VISIBLE);
                layout4.setVisibility(View.GONE);
            }
        });
        vetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  temp.setBackgroundColor(Color.parseColor("#CAD1D3"));
              //  sunce.setBackgroundColor(Color.parseColor("#CAD1D3"));
              //  vetar.setBackgroundColor(Color.BLUE);

                vetar.setTextColor(Color.BLACK);
                temp.setTextColor(Color.WHITE);
                sunce.setTextColor(Color.WHITE);

                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.GONE);
                layout4.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {}

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
