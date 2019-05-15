package pnrs.vezbe.projekat_1;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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
    TextView[] temps = {temp_pon,temp_uto,temp_sre,temp_cet,temp_pet,temp_sub,temp_ned};
    TextView[] presss = {press_pon,press_uto,press_sre,press_cet,press_pet,press_sub,press_ned};
    TextView[] hums = {hum_pon,hum_uto,hum_sre,hum_cet,hum_pet,hum_sub,hum_ned};
    TextView[] dani = {pon,uto,sre,cet,pet,sub,ned};
    TextView temp_max,temp_min,dan_max,dan_min;
    ImageView sunce,pahulja;

    String grad,danas;



    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);
        dbHelper=new StatisticsDbHelper(this);
        sunce=findViewById(R.id.slika1);
        pahulja=findViewById(R.id.slika2);
        temp_max=findViewById(R.id.temp_max);
        temp_min=findViewById(R.id.temp_min);
        dan_max=findViewById(R.id.dan_max);
        dan_min=findViewById(R.id.dan_min);
        final String[] lista_dana={"Ponedeljak","Utorak","Sreda","Četvrtak","Petak","Subota","Nedelja"};

        danas = getToday();
        pon=findViewById(R.id.pon);
        uto=findViewById(R.id.uto);
        sre=findViewById(R.id.sre);
        cet=findViewById(R.id.cet);
        pet=findViewById(R.id.pet);
        sub=findViewById(R.id.sub);
        ned=findViewById(R.id.ned);
        dani[0]=findViewById(R.id.pon);
        dani[1]=findViewById(R.id.uto);
        dani[2]=findViewById(R.id.sre);
        dani[3]=findViewById(R.id.cet);
        dani[4]=findViewById(R.id.pet);
        dani[5]=findViewById(R.id.sub);
        dani[6]=findViewById(R.id.ned);


        ime_grada=findViewById(R.id.ime_grada);
        temp_pon = findViewById(R.id.temp_pon);
        temp_uto = findViewById(R.id.temp_uto);
        temp_sre = findViewById(R.id.temp_sre);
        temp_cet = findViewById(R.id.temp_cet);
        temp_pet = findViewById(R.id.temp_pet);
        temp_sub = findViewById(R.id.temp_sub);
        temp_ned = findViewById(R.id.temp_ned);
        temps[0]=findViewById(R.id.temp_pon);
        temps[1]=findViewById(R.id.temp_uto);
        temps[2]=findViewById(R.id.temp_sre);
        temps[3]=findViewById(R.id.temp_cet);
        temps[4]=findViewById(R.id.temp_pet);
        temps[5]=findViewById(R.id.temp_sub);
        temps[6]=findViewById(R.id.temp_ned);


        press_pon = findViewById(R.id.press_pon);
        press_uto = findViewById(R.id.press_uto);
        press_sre = findViewById(R.id.press_sre);
        press_cet = findViewById(R.id.press_cet);
        press_pet = findViewById(R.id.press_pet);
        press_sub = findViewById(R.id.press_sub);
        press_ned = findViewById(R.id.press_ned);
        presss[0]=findViewById(R.id.press_pon);
        presss[1]=findViewById(R.id.press_uto);
        presss[2]=findViewById(R.id.press_sre);
        presss[3]=findViewById(R.id.press_cet);
        presss[4]=findViewById(R.id.press_pet);
        presss[5]=findViewById(R.id.press_sub);
        presss[6]=findViewById(R.id.press_ned);

        hum_pon = findViewById(R.id.hum_pon);
        hum_uto = findViewById(R.id.hum_uto);
        hum_sre = findViewById(R.id.hum_sre);
        hum_cet = findViewById(R.id.hum_cet);
        hum_pet = findViewById(R.id.hum_pet);
        hum_sub = findViewById(R.id.hum_sub);
        hum_ned = findViewById(R.id.hum_ned);
        hums[0]=findViewById(R.id.hum_pon);
        hums[1]=findViewById(R.id.hum_uto);
        hums[2]=findViewById(R.id.hum_sre);
        hums[3]=findViewById(R.id.hum_cet);
        hums[4]=findViewById(R.id.hum_pet);
        hums[5]=findViewById(R.id.hum_sub);
        hums[6]=findViewById(R.id.hum_ned);


        grad=getIntent().getStringExtra("grad");
        dbHelper.deleteCity(grad);
        final String dan=getIntent().getStringExtra("dan");
        final String temp =getIntent().getStringExtra("temperatura");
        final String humidity = getIntent().getStringExtra("humidity");
        final String pressure = getIntent().getStringExtra("pressure");
        ime_grada.setText(grad);





        final Forecast forecast_pon = new Forecast("Ponedeljak",grad,"19","1003mb","85%");
        final Forecast forecast_uto = new Forecast("Utorak",grad,"10","1012mb","90%");
        final Forecast forecast_sre = new Forecast("Sreda",grad,"11","1015mb","95%");
        final Forecast forecast_cet = new Forecast("Četvrtak",grad,"17","1009mb","83%");
        final Forecast forecast_pet = new Forecast("Petak",grad,"26","1012mb","90%");
        final Forecast forecast_sub = new Forecast("Subota",grad,"26","1023mb","70%");
        final Forecast forecast_ned = new Forecast("Nedelja",grad,"26","1011mb","81%");

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
       // Forecast[] forecasts= dbHelper.readForecasts();
        dbHelper.deleteForecast(grad,danas);
        dbHelper.insert(new Forecast(danas,grad,String.valueOf(Double.valueOf(temp).intValue()),pressure+"mb",humidity+"%"));


        //Forecast forecast = dbHelper.readForecast("Novi Sad","Subota");

        temp_pon.setText(dbHelper.readForecast(grad,"Ponedeljak").getTemp()+"°C");
        temp_uto.setText(dbHelper.readForecast(grad,"Utorak").getTemp()+"°C");
        temp_sre.setText(dbHelper.readForecast(grad,"Sreda").getTemp()+"°C");
        temp_cet.setText(dbHelper.readForecast(grad,"Četvrtak").getTemp()+"°C");
        temp_pet.setText(dbHelper.readForecast(grad,"Petak").getTemp()+"°C");
        temp_sub.setText(dbHelper.readForecast(grad,"Subota").getTemp()+"°C");
        temp_ned.setText(dbHelper.readForecast(grad,"Nedelja").getTemp()+"°C");

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
        Forecast[] forecasts=dbHelper.readForecasts(grad);
        Forecast maxForecast=new Forecast("0","0","0","0","0");
        Forecast minForecast=new Forecast("100","100","100","100","100");
        for(int i=0;i<forecasts.length;i++){
            if (Integer.valueOf(forecasts[i].getTemp())>Integer.valueOf(maxForecast.getTemp())) {
                 maxForecast=forecasts[i];

                maxForecast.setDan(forecasts[i].getDan());
                maxForecast.setGrad(forecasts[i].getGrad());
                maxForecast.setHumidity(forecasts[i].getHumidity());
                maxForecast.setPressure(forecasts[i].getPressure());
                maxForecast.setTemp(forecasts[i].getTemp());
            }
        }

        temp_max.setText(maxForecast.getTemp()+"°C");
        dan_max.setText(dbHelper.findForecast(grad,maxForecast.getTemp()).getDan());
        dbHelper.deleteForecast(maxForecast.getGrad(),maxForecast.getDan());

        for(int i=0;i<forecasts.length;i++){
            if((Integer.valueOf(maxForecast.getTemp())==Integer.valueOf(forecasts[i].getTemp()))&&forecasts[i].getDan()!=maxForecast.getDan()){
                dan_max.append("\n"+dbHelper.findForecast(grad,forecasts[i].getTemp()).getDan());
                dbHelper.deleteForecast(grad,forecasts[i].getDan());
            }
        }

        for(int i=0;i<forecasts.length;i++){
            if (Integer.valueOf(forecasts[i].getTemp())<Integer.valueOf(minForecast.getTemp())) {
                minForecast=forecasts[i];
                minForecast.setDan(forecasts[i].getDan());
                minForecast.setGrad(forecasts[i].getGrad());
                minForecast.setHumidity(forecasts[i].getHumidity());
                minForecast.setPressure(forecasts[i].getPressure());
                minForecast.setTemp(forecasts[i].getTemp());
            }
        }
        temp_min.setText(minForecast.getTemp()+"°C");
        dan_min.setText(dbHelper.findForecast(grad,minForecast.getTemp()).getDan());
        dbHelper.deleteForecast(minForecast.getGrad(),minForecast.getDan());

        for(int i=0;i<forecasts.length;i++){
            if((Integer.valueOf(minForecast.getTemp())==Integer.valueOf(forecasts[i].getTemp()))&&forecasts[i].getDan()!=minForecast.getDan()){
                dan_min.append("\n"+dbHelper.findForecast(grad,forecasts[i].getTemp()).getDan());
                dbHelper.deleteForecast(grad,forecasts[i].getDan());
            }
        }

        pahulja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteCity(grad);
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
                // Forecast[] forecasts= dbHelper.readForecasts();
                dbHelper.deleteForecast(grad,danas);
                dbHelper.insert(new Forecast(danas,grad,String.valueOf(Double.valueOf(temp).intValue()),pressure+"mb",humidity+"%"));


                for(int i=0;i<lista_dana.length;i++){
                    if(Integer.valueOf(dbHelper.readForecast(grad,lista_dana[i]).getTemp())>10){
                         temps[i].setText(dbHelper.readForecast(grad,lista_dana[i]).getTemp()+ "°C");
                         hums[i].setText(dbHelper.readForecast(grad,lista_dana[i]).getHumidity());
                         presss[i].setText(dbHelper.readForecast(grad,lista_dana[i]).getPressure());
                         dani[i].setTypeface(null,Typeface.BOLD);

                        }
                        else{
                            temps[i].setText("");
                            hums[i].setText("");
                            presss[i].setText("");
                            dani[i].setTypeface(null,Typeface.NORMAL);
                        }
                    }
                }


        });

        sunce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteCity(grad);
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
                // Forecast[] forecasts= dbHelper.readForecasts();
                dbHelper.deleteForecast(grad,danas);
                dbHelper.insert(new Forecast(danas,grad,String.valueOf(Double.valueOf(temp).intValue()),pressure+"mb",humidity+"%"));


                for(int i=0;i<lista_dana.length;i++){
                    if(Integer.valueOf(dbHelper.readForecast(grad,lista_dana[i]).getTemp())<10){
                        temps[i].setText(dbHelper.readForecast(grad,lista_dana[i]).getTemp()+ "°C");
                        hums[i].setText(dbHelper.readForecast(grad,lista_dana[i]).getHumidity());
                        presss[i].setText(dbHelper.readForecast(grad,lista_dana[i]).getPressure());
                        dani[i].setTypeface(null,Typeface.BOLD);

                    }
                    else{
                        temps[i].setText("");
                        hums[i].setText("");
                        presss[i].setText("");
                        dani[i].setTypeface(null,Typeface.NORMAL);
                    }
                }
            }


        });


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
