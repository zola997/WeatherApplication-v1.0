package pnrs.vezbe.projekat_1;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ime_grada;
    private StatisticsDbHelper dbHelper;
    TextView pon,uto,sre,cet,pet,sub,ned;
    TextView temp_pon,temp_uto,temp_sre,temp_cet,temp_pet,temp_sub,temp_ned;
    TextView press_pon,press_uto,press_sre,press_cet,press_pet,press_sub,press_ned;
    TextView hum_pon,hum_uto,hum_sre,hum_cet,hum_pet,hum_sub,hum_ned;

    String grad,danas;



    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);
        dbHelper=new StatisticsDbHelper(this);

        danas = getToday();
        pon=findViewById(R.id.pon);
        uto=findViewById(R.id.uto);
        sre=findViewById(R.id.sre);
        cet=findViewById(R.id.cet);
        pet=findViewById(R.id.pet);
        sub=findViewById(R.id.sub);
        ned=findViewById(R.id.ned);

        ime_grada=findViewById(R.id.ime_grada);
        temp_pon = findViewById(R.id.temp_pon);
        temp_uto = findViewById(R.id.temp_uto);
        temp_sre = findViewById(R.id.temp_sre);
        temp_cet = findViewById(R.id.temp_cet);
        temp_pet = findViewById(R.id.temp_pet);
        temp_sub = findViewById(R.id.temp_sub);
        temp_ned = findViewById(R.id.temp_ned);

        press_pon = findViewById(R.id.press_pon);
        press_uto = findViewById(R.id.press_uto);
        press_sre = findViewById(R.id.press_sre);
        press_cet = findViewById(R.id.press_cet);
        press_pet = findViewById(R.id.press_pet);
        press_sub = findViewById(R.id.press_sub);
        press_ned = findViewById(R.id.press_ned);

        hum_pon = findViewById(R.id.hum_pon);
        hum_uto = findViewById(R.id.hum_uto);
        hum_sre = findViewById(R.id.hum_sre);
        hum_cet = findViewById(R.id.hum_cet);
        hum_pet = findViewById(R.id.hum_pet);
        hum_sub = findViewById(R.id.hum_sub);
        hum_ned = findViewById(R.id.hum_ned);

        grad=getIntent().getStringExtra("grad");
        dbHelper.deleteCity(grad);
        final String dan=getIntent().getStringExtra("dan");
        final String temp =getIntent().getStringExtra("temperatura");
        final String humidity = getIntent().getStringExtra("humidity");
        final String pressure = getIntent().getStringExtra("pressure");
        ime_grada.setText(grad);




        Forecast forecast_pon = new Forecast("Ponedeljak",grad,"19°C","1003mb","85%");
        Forecast forecast_uto = new Forecast("Utorak",grad,"10°C","1012mb","90%");
        Forecast forecast_sre = new Forecast("Sreda",grad,"13°C","1015mb","95%");
        Forecast forecast_cet = new Forecast("Četvrtak",grad,"17°C","1009mb","83%");
        Forecast forecast_pet = new Forecast("Petak",grad,"21°C","1012mb","90%");
        Forecast forecast_sub = new Forecast("Subota",grad,"24°C","1023mb","70%");
        Forecast forecast_ned = new Forecast("Nedelja",grad,"26°C","1011mb","81%");

        if(danas!="Ponedeljak")
        dbHelper.insert(forecast_pon);
        if(danas!="Utorak")
        dbHelper.insert(forecast_uto);
        if(danas!="Sreda")
        dbHelper.insert(forecast_sre);
        if(danas!="Četvrtak")
        dbHelper.insert(forecast_cet);
        if(danas!="Petak")
        dbHelper.insert(forecast_pet);
        if(danas!="Subota")
        dbHelper.insert(forecast_sub);
        if(danas!="Nedelja")
        dbHelper.insert(forecast_ned);
        Forecast[] forecasts= dbHelper.readForecasts();
        dbHelper.deleteForecast(grad,danas);
        dbHelper.insert(new Forecast(danas,grad,temp+"°C",pressure+"mb",humidity+"%"));


        //Forecast forecast = dbHelper.readForecast("Novi Sad","Subota");

        temp_pon.setText(dbHelper.readForecast(grad,"Ponedeljak").getTemp());
        temp_uto.setText(dbHelper.readForecast(grad,"Utorak").getTemp());
        temp_sre.setText(dbHelper.readForecast(grad,"Sreda").getTemp());
        temp_cet.setText(dbHelper.readForecast(grad,"Četvrtak").getTemp());
        temp_pet.setText(dbHelper.readForecast(grad,"Petak").getTemp());
        temp_sub.setText(dbHelper.readForecast(grad,"Subota").getTemp());
        temp_ned.setText(dbHelper.readForecast(grad,"Nedelja").getTemp());

        press_pon.setText(dbHelper.readForecast(grad,"Ponedeljak").getPressure());
        press_uto.setText(dbHelper.readForecast(grad,"Utorak").getPressure());
        press_sre.setText(dbHelper.readForecast(grad,"Sreda").getPressure());
        press_cet.setText(dbHelper.readForecast(grad,"Četvrtak").getPressure());
        press_pet.setText(dbHelper.readForecast(grad,"Petak").getPressure());
        press_sub.setText(dbHelper.readForecast(grad,"Subota").getPressure());
        press_ned.setText(dbHelper.readForecast(grad,"Nedelja").getPressure());

        hum_pon.setText(dbHelper.readForecast(grad,"Ponedeljak").getHumidity());
        hum_uto.setText(dbHelper.readForecast(grad,"Utorak").getHumidity());
        hum_sre.setText(dbHelper.readForecast(grad,"Sreda").getHumidity());
        hum_cet.setText(dbHelper.readForecast(grad,"Četvrtak").getHumidity());
        hum_pet.setText(dbHelper.readForecast(grad,"Petak").getHumidity());
        hum_sub.setText(dbHelper.readForecast(grad,"Subota").getHumidity());
        hum_ned.setText(dbHelper.readForecast(grad,"Nedelja").getHumidity());



        if (danas == "Ponedeljak"){
            pon.setTypeface(null,Typeface.BOLD);
            temp_pon.setTypeface(null, Typeface.BOLD);
            press_pon.setTypeface(null, Typeface.BOLD);
            hum_pon.setTypeface(null,Typeface.BOLD);
        }
        else if(danas == "Utorak"){
            uto.setTypeface(null,Typeface.BOLD);
            temp_uto.setTypeface(null, Typeface.BOLD);
            press_uto.setTypeface(null, Typeface.BOLD);
            hum_uto.setTypeface(null,Typeface.BOLD);
        }
        else if(danas == "Sreda"){
            sre.setTypeface(null,Typeface.BOLD);
            temp_sre.setTypeface(null, Typeface.BOLD);
            press_sre.setTypeface(null, Typeface.BOLD);
            hum_sre.setTypeface(null,Typeface.BOLD);
        }
        else if(danas == "Četvrtak"){
            cet.setTypeface(null,Typeface.BOLD);
            temp_cet.setTypeface(null, Typeface.BOLD);
            press_cet.setTypeface(null, Typeface.BOLD);
            hum_cet.setTypeface(null,Typeface.BOLD);
        }
        else if(danas == "Petak") {
            pet.setTypeface(null,Typeface.BOLD);
            temp_pet.setTypeface(null, Typeface.BOLD);
            press_pet.setTypeface(null, Typeface.BOLD);
            hum_pet.setTypeface(null,Typeface.BOLD);
        }
        else if(danas == "Subota"){
            sub.setTypeface(null,Typeface.BOLD);
            temp_sub.setTypeface(null, Typeface.BOLD);
            press_sub.setTypeface(null, Typeface.BOLD);
            hum_sub.setTypeface(null,Typeface.BOLD);
        }
        else if(danas == "Nedelja"){
            ned.setTypeface(null,Typeface.BOLD);
            temp_ned.setTypeface(null, Typeface.BOLD);
            press_ned.setTypeface(null, Typeface.BOLD);
            hum_ned.setTypeface(null,Typeface.BOLD);
        }
    }

    @Override
    public void onClick(View view) {

    }

    public String getToday(){
        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("EE");
        final String formattedDate = df.format(c.getTime());
        if(formattedDate.toUpperCase().equals("ПОН")|| formattedDate.toUpperCase().equals("MON")){
            return "Ponedeljak";
        }
        else if (formattedDate.toUpperCase().equals("УТО")|| formattedDate.toUpperCase().equals("TUE")){
            return "Utorak";
        }
        else if (formattedDate.toUpperCase().equals("СРЕ")|| formattedDate.toUpperCase().equals("WED")){
            return "Sreda";
        }
        else if (formattedDate.toUpperCase().equals("ЧЕТ")|| formattedDate.toUpperCase().equals("THU")){
            return "Četvrtak";
        }
        else if(formattedDate.toUpperCase().equals("ПЕТ")|| formattedDate.toUpperCase().equals("FRI")){
            return "Petak";
        }
        else if(formattedDate.toUpperCase().equals("СУБ")|| formattedDate.toUpperCase().equals("SAT")){
            return "Subota";
        }
        else if(formattedDate.toUpperCase().equals("НЕД")|| formattedDate.toUpperCase().equals("SUN")){
            return "Nedelja";
        }

        return "";
    }
}
