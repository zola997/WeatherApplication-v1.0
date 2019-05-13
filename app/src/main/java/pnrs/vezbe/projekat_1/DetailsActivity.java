package pnrs.vezbe.projekat_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static pnrs.vezbe.projekat_1.R.drawable;

public class DetailsActivity<intent> extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    double celzijus,farenhajt;
    TextView ime_grada,dan,pritisak,vlaznost,izlazak_zalazak,vetar;
    LinearLayout layout2,layout3,layout4;
    Button temp,sunce,vetar_button;
    String temp_kelvin,pressure,humidity,wind_dir,wind_speed,sunrise,sunset,weather1;
    ImageView slika;
    private Http http;
    private static String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static String API_KEY = "&APPID=6db369e4771614a635375634c3e40b8d";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String grad;

        if (getIntent().hasExtra("grad")) {
            grad = getIntent().getStringExtra("grad");
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + "grad");
        }

        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = new Date();
        String filename = timeStampFormat.format(myDate);

        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("EE");
        String formattedDate = df.format(c.getTime());
        http = new Http();

        pritisak=(TextView) findViewById(R.id.textView8);
        vlaznost=(TextView) findViewById(R.id.textView9);
        izlazak_zalazak=(TextView) findViewById(R.id.textView6);
        vetar=(TextView) findViewById(R.id.textView10);
        slika=(ImageView) findViewById(R.id.slika);

        new Thread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {

                    final String URL = API_URL + grad + API_KEY;
                    pritisak = (TextView) findViewById(R.id.textView8);
                    vlaznost = (TextView) findViewById(R.id.textView9);
                    izlazak_zalazak = (TextView) findViewById(R.id.textView6);
                    vetar = (TextView) findViewById(R.id.textView10);

                    JSONObject jsonObject = http.getJSONObjectFromURL(URL);
                    if (jsonObject == null) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("error","Unable to connect.");
                        getApplicationContext().startActivity(intent);


                    } else {

                        JSONObject wind = (JSONObject) jsonObject.get("wind");
                        JSONObject main = (JSONObject) jsonObject.get("main");

                        temp_kelvin = main.getString("temp");
                        celzijus = Double.parseDouble(temp_kelvin) - 273.15;
                        farenhajt = celzijus * 9 / 5 + 32;
                        try {
                            pressure = main.getString("pressure");
                        } catch (JSONException e) {
                            pritisak.setText("Pressure data not available");
                        }
                        try {
                            humidity = main.getString("humidity");
                        } catch (JSONException e) {
                            vlaznost.setText("Humidity data not available");
                            e.printStackTrace();
                        }
                        try {

                            wind_dir = wind.getString("deg");
                        } catch (JSONException e) {
                            vetar.setText("Wind direction not available.");
                            e.printStackTrace();
                        }
                        try {
                            wind_speed = wind.getString("speed");
                        } catch (JSONException e) {
                            vetar.setText("Wind speed not available.");
                            e.printStackTrace();
                        }

                        JSONObject sys = (JSONObject) jsonObject.get("sys");
                        sunrise = sys.getString("sunrise");
                        sunset = sys.getString("sunset");
                        pritisak.setText("Pritisak: " + String.valueOf(pressure) + "mb");
                        vlaznost.setText("Vlažnost: " + String.valueOf(humidity) + "%");
                        JSONArray weather = (JSONArray) jsonObject.get("weather");
                        JSONObject object = weather.getJSONObject(0);
                        weather1 = object.getString("main");
                        slika = (ImageView) findViewById(R.id.slika);

                        switch (weather1) {
                            case "Rain":
                                slika.setImageDrawable(getResources().getDrawable(drawable.rain_icon));
                                break;
                            case "Clouds":
                                slika.setImageDrawable(getResources().getDrawable(drawable.clouds_icon));
                                break;
                            case "Clear":
                                slika.setImageDrawable(getResources().getDrawable(drawable.sun_icon));
                                break;
                            default:
                                break;
                        }

                        Date date = new java.util.Date(Integer.parseInt(sunrise) * 1000L);

                        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
                        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
                        String formattedDate = sdf.format(date);

                        Date date1 = new java.util.Date(Integer.parseInt(sunset) * 1000L);

                        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("HH:mm:ss");
                        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
                        String formattedDate1 = sdf1.format(date1);

                        izlazak_zalazak.setText("Izlazak sunca: " + formattedDate + "\n" + "Zalazak sunca: " + formattedDate1);
                        if (wind_dir != null && wind_speed != null) {
                            Double pravac = Double.parseDouble(wind_dir);
                            if (pravac >= 337 || pravac < 23) {
                                vetar.setText("Brzina vetra: " + wind_speed + "m/s\n" + "Pravac: N");
                            } else if (pravac >= 23 && pravac < 68) {
                                vetar.setText("Brzina vetra: " + wind_speed + "m/s\n" + "Pravac: NE");
                            } else if (pravac >= 68 && pravac < 113) {
                                vetar.setText("Brzina vetra: " + wind_speed + "m/s\n" + "Pravac: E");
                            } else if (pravac >= 113 && pravac < 158) {
                                vetar.setText("Brzina vetra: " + wind_speed + "m/s\n" + "Pravac: SE");
                            } else if (pravac >= 158 && pravac < 203) {
                                vetar.setText("Brzina vetra: " + wind_speed + "m/s\n" + "Pravac: S");
                            } else if (pravac >= 203 && pravac < 248) {
                                vetar.setText("Brzina vetra: " + wind_speed + "m/s\n" + "Pravac: SW");
                            } else if (pravac >= 248 && pravac < 293) {
                                vetar.setText("Brzina vetra: " + wind_speed + "m/s\n" + "Pravac: W");
                            } else if (pravac >= 293 && pravac < 337) {
                                vetar.setText("Brzina vetra: " + wind_speed + "m/s\n" + "Pravac: NW");
                            }
                        } else if (wind_speed != null && wind_dir == null) {
                            vetar.setText("Brzina vetra: " + wind_speed + "m/s\n" + "Wind direction not available.");
                        } else if (wind_speed == null && wind_dir != null) {
                            Double pravac = Double.parseDouble(wind_dir);
                            if (pravac >= 337 || pravac < 23) {
                                vetar.setText("Wind speed not available.\n" + "Pravac: N");
                            } else if (pravac >= 23 && pravac < 68) {
                                vetar.setText("Wind speed not available.\n" + "Pravac: NE");
                            } else if (pravac >= 68 && pravac < 113) {
                                vetar.setText("Wind speed not available.\n" + "Pravac: E");
                            } else if (pravac >= 113 && pravac < 158) {
                                vetar.setText("Wind speed not available.\n" + "Pravac: SE");
                            } else if (pravac >= 158 && pravac < 203) {
                                vetar.setText("Wind speed not available.\n" + "Pravac: S");
                            } else if (pravac >= 203 && pravac < 248) {
                                vetar.setText("Wind speed not available.\n" + "Pravac: SW");
                            } else if (pravac >= 248 && pravac < 293) {
                                vetar.setText("Wind speed not available.\n" + "Pravac: W");
                            } else if (pravac >= 293 && pravac < 337) {
                                vetar.setText("Wind speed not available.\n" + "Pravac: NW");
                            }
                        }
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        setContentView(R.layout.activity_details);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        ime_grada=(TextView) findViewById(R.id.TextView1);
        dan=(TextView) findViewById(R.id.textView4);
        ime_grada.setText(grad);
        pritisak=(TextView) findViewById(R.id.textView8);
        vlaznost=(TextView) findViewById(R.id.textView9);
        izlazak_zalazak=findViewById(R.id.textView6);
        vetar=(TextView) findViewById(R.id.textView10);
        final TextView temperatura = (TextView) findViewById(R.id.textView7);

        dan.setText(formattedDate.toUpperCase()+" "+filename);
        String[] items = new String[]{"°C","°F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String string = adapterView.getSelectedItem().toString();
               switch (string){
                   case "°F":
                       NumberFormat form = new DecimalFormat("#0.0");
                       temperatura.setText("Temp:" + form.format(farenhajt) + " °F");
                       break;
                   case "°C":
                       NumberFormat form1 = new DecimalFormat("#0.0");
                       temperatura.setText("Temp:" + form1.format(celzijus) + " °C");
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
        vetar_button =(Button) findViewById(R.id.vetar);

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
                vetar_button.setTextColor(Color.WHITE);

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
                vetar_button.setTextColor(Color.WHITE);

                layout2.setVisibility(View.GONE);
                layout3.setVisibility(View.VISIBLE);
                layout4.setVisibility(View.GONE);
            }
        });
        vetar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vetar_button.setTextColor(Color.BLACK);
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


