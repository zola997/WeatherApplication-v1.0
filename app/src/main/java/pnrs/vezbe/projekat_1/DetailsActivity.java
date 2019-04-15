package pnrs.vezbe.projekat_1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class DetailsActivity<intent> extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    double celzijus;
    double farenhajt;
    TextView ime_grada,dan;
    LinearLayout layout2,layout3,layout4;
    Button temp,sunce,vetar;
    String temp_kelvin,pressure,humidity,wind_dir,wind_speed,sunrise,sunset;
    private Http http;
    public static String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static String API_KEY = "&APPID=6db369e4771614a635375634c3e40b8d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String grad;

        if (getIntent().hasExtra("grad")) {
            grad = getIntent().getStringExtra("grad");
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + "grad");
        }

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = new Date();
        String filename = timeStampFormat.format(myDate);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EE");
        String formattedDate = df.format(c.getTime());

        //////////////// HTTP //////////////

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    final String URL = API_URL + grad + API_KEY;
                    JSONObject jsonObject = http.getJSONObjectFromURL(URL);
                    JSONObject main = (JSONObject) jsonObject.get("main");
                    temp_kelvin=main.getString("temp");
                    pressure=main.getString("pressure");
                    humidity=main.getString("humidity");
                    JSONObject wind = (JSONObject) jsonObject.get("wind");
                    wind_dir = wind.getString("deg");
                    wind_speed = wind.getString("speed");
                    JSONObject sys = (JSONObject) jsonObject.get("sys");
                    sunrise = sys.getString("sunrise");
                    sunset = sys.getString("sunset");



                }
                catch (JSONException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



          ////////////////////////////////////////
        setContentView(R.layout.activity_details);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        ime_grada=(TextView) findViewById(R.id.TextView1);
        dan=(TextView) findViewById(R.id.textView4);
        ime_grada.setText(grad);
        celzijus=Integer.parseInt(temp_kelvin) - 273.15;
        farenhajt= Integer.parseInt(temp_kelvin)*(9/5) -459.67;

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
                       temperatura.setText(String.valueOf(farenhajt) +" °F");
                       break;
                   case "C":
                       temperatura.setText(String.valueOf(celzijus)+" °C");
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


