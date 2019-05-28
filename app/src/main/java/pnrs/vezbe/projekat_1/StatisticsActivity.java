package pnrs.vezbe.projekat_1;


import android.annotation.SuppressLint;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    Button brisi_sredu;
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
        brisi_sredu=findViewById(R.id.brisi_sredu);


        grad=getIntent().getStringExtra("grad");
       // dbHelper.deleteCity(grad);
        dbHelper.deleteAll();
        final String dan=getIntent().getStringExtra("dan");
        final String temp =getIntent().getStringExtra("temperatura");
        final String humidity = getIntent().getStringExtra("humidity");
        final String pressure = getIntent().getStringExtra("pressure");
        ime_grada.setText(grad);

        for(int i=0;i<7;i++) {
            dbHelper.insert(new Forecast(lista_dana[i], grad,temps[i].getText().toString(),presss[i].getText().toString(),hums[i].getText().toString()));
            temps[i].append("°C");
        }

        dbHelper.deleteForecast(grad,danas);
        dbHelper.insert(new Forecast(danas,grad,String.valueOf(Double.valueOf(temp).intValue()),pressure+"mb",humidity+"%"));


        if (danas == "Ponedeljak"){
            pon.setTypeface(null,Typeface.BOLD);
            temp_pon.setTypeface(null, Typeface.BOLD);
            press_pon.setTypeface(null, Typeface.BOLD);
            hum_pon.setTypeface(null,Typeface.BOLD);
            temp_pon.setText(dbHelper.readForecast(grad,danas).getTemp()+"°C");
            press_pon.setText(dbHelper.readForecast(grad,danas).getPressure());
            hum_pon.setText(dbHelper.readForecast(grad,danas).getHumidity());
        }
        else if(danas == "Utorak"){
            uto.setTypeface(null,Typeface.BOLD);
            temp_uto.setTypeface(null, Typeface.BOLD);
            press_uto.setTypeface(null, Typeface.BOLD);
            hum_uto.setTypeface(null,Typeface.BOLD);
            temp_uto.setText(dbHelper.readForecast(grad,danas).getTemp()+"°C");
            press_uto.setText(dbHelper.readForecast(grad,danas).getPressure());
            hum_uto.setText(dbHelper.readForecast(grad,danas).getHumidity());
        }
        else if(danas == "Sreda"){
            sre.setTypeface(null,Typeface.BOLD);
            temp_sre.setTypeface(null, Typeface.BOLD);
            press_sre.setTypeface(null, Typeface.BOLD);
            hum_sre.setTypeface(null,Typeface.BOLD);
            temp_sre.setText(dbHelper.readForecast(grad,danas).getTemp()+"°C");
            press_sre.setText(dbHelper.readForecast(grad,danas).getPressure());
            hum_sre.setText(dbHelper.readForecast(grad,danas).getHumidity());
        }
        else if(danas == "Četvrtak"){
            cet.setTypeface(null,Typeface.BOLD);
            temp_cet.setTypeface(null, Typeface.BOLD);
            press_cet.setTypeface(null, Typeface.BOLD);
            hum_cet.setTypeface(null,Typeface.BOLD);
            temp_cet.setText(dbHelper.readForecast(grad,danas).getTemp()+"°C");
            press_cet.setText(dbHelper.readForecast(grad,danas).getPressure());
            hum_cet.setText(dbHelper.readForecast(grad,danas).getHumidity());
        }
        else if(danas == "Petak") {
            pet.setTypeface(null,Typeface.BOLD);
            temp_pet.setTypeface(null, Typeface.BOLD);
            press_pet.setTypeface(null, Typeface.BOLD);
            hum_pet.setTypeface(null,Typeface.BOLD);
            temp_pet.setText(dbHelper.readForecast(grad,danas).getTemp()+"°C");
            press_pet.setText(dbHelper.readForecast(grad,danas).getPressure());
            hum_pet.setText(dbHelper.readForecast(grad,danas).getHumidity());
        }
        else if(danas == "Subota"){
            sub.setTypeface(null,Typeface.BOLD);
            temp_sub.setTypeface(null, Typeface.BOLD);
            press_sub.setTypeface(null, Typeface.BOLD);
            hum_sub.setTypeface(null,Typeface.BOLD);
            temp_sub.setText(dbHelper.readForecast(grad,danas).getTemp()+"°C");
            press_sub.setText(dbHelper.readForecast(grad,danas).getPressure());
            hum_sub.setText(dbHelper.readForecast(grad,danas).getHumidity());
        }
        else if(danas == "Nedelja"){
            ned.setTypeface(null,Typeface.BOLD);
            temp_ned.setTypeface(null, Typeface.BOLD);
            press_ned.setTypeface(null, Typeface.BOLD);
            hum_ned.setTypeface(null,Typeface.BOLD);
            temp_ned.setText(dbHelper.readForecast(grad,danas).getTemp()+"°C");
            press_ned.setText(dbHelper.readForecast(grad,danas).getPressure());
            hum_ned.setText(dbHelper.readForecast(grad,danas).getHumidity());
        }

        Forecast[] forecasts=dbHelper.readForecasts(grad);
        Forecast maxForecast=new Forecast("0","0","0","0","0");
        Forecast minForecast=new Forecast("100","100","100","100","100");
        try {
            for (int i = 0; i < forecasts.length; i++) {

                if (Integer.valueOf(forecasts[i].getTemp()) > Integer.valueOf(maxForecast.getTemp())) {
                    maxForecast = forecasts[i];
                    maxForecast.setDan(forecasts[i].getDan());
                    maxForecast.setGrad(forecasts[i].getGrad());
                    maxForecast.setHumidity(forecasts[i].getHumidity());
                    maxForecast.setPressure(forecasts[i].getPressure());
                    maxForecast.setTemp(forecasts[i].getTemp());
                }
            }


        temp_max.setText(maxForecast.getTemp()+"°C");
        dan_max.setText(dbHelper.findForecast(grad,maxForecast.getTemp()).getDan());
        }catch (NumberFormatException n){
            n.printStackTrace();
        }

        try {
            for (int i = 0; i < forecasts.length; i++) {
                if ((Integer.valueOf(maxForecast.getTemp()) == Integer.valueOf(forecasts[i].getTemp())) && forecasts[i].getDan() != maxForecast.getDan() && (!dan_max.getText().toString().contains(forecasts[i].getDan()))) {
                    dan_max.append("\n" + forecasts[i].getDan());

                }
            }
        }
        catch (NumberFormatException n ){
            n.printStackTrace();
        }
       try {
           for (int i = 0; i < forecasts.length; i++) {
               if (Integer.valueOf(forecasts[i].getTemp()) < Integer.valueOf(minForecast.getTemp())) {
                   minForecast = forecasts[i];
                   minForecast.setDan(forecasts[i].getDan());
                   minForecast.setGrad(forecasts[i].getGrad());
                   minForecast.setHumidity(forecasts[i].getHumidity());
                   minForecast.setPressure(forecasts[i].getPressure());
                   minForecast.setTemp(forecasts[i].getTemp());
               }
           }
           temp_min.setText(minForecast.getTemp() + "°C");
           dan_min.setText(dbHelper.findForecast(grad, minForecast.getTemp()).getDan());
       }catch (NumberFormatException n){
           n.printStackTrace();
       }

       try {
           for (int i = 0; i < forecasts.length; i++) {
               if ((Integer.valueOf(minForecast.getTemp()) == Integer.valueOf(forecasts[i].getTemp())) && forecasts[i].getDan() != minForecast.getDan() && (!dan_min.getText().toString().contains(forecasts[i].getDan()))) {
                   dan_min.append("\n" + forecasts[i].getDan());
               }
           }
       }catch (NumberFormatException n){
           n.printStackTrace();
       }

        pahulja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.deleteForecast(grad, danas);
                dbHelper.insert(new Forecast(danas, grad, String.valueOf(Double.valueOf(temp).intValue()), pressure + "mb", humidity + "%"));

                    for (int i = 0; i < lista_dana.length; i++) {
                        try {
                            if (Integer.valueOf(dbHelper.readForecast(grad, lista_dana[i]).getTemp()) > 10) {
                                temps[i].setText(dbHelper.readForecast(grad, lista_dana[i]).getTemp() + "°C");
                                hums[i].setText(dbHelper.readForecast(grad, lista_dana[i]).getHumidity());
                                presss[i].setText(dbHelper.readForecast(grad, lista_dana[i]).getPressure());
                                dani[i].setTypeface(null, Typeface.BOLD);

                            } else {
                                temps[i].setText("");
                                hums[i].setText("");
                                presss[i].setText("");
                                dani[i].setTypeface(null, Typeface.NORMAL);
                            }
                        }catch (CursorIndexOutOfBoundsException e){
                            e.printStackTrace();
                    }

                }
            }


        });

        sunce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Forecast[] forecasts = dbHelper.readForecasts(grad);
                dbHelper.deleteForecast(grad, danas);
                dbHelper.insert(new Forecast(danas, grad, String.valueOf(Double.valueOf(temp).intValue()), pressure + "mb", humidity + "%"));


                for (int i = 0; i < lista_dana.length; i++) {
                    try {
                        if (Integer.valueOf(dbHelper.readForecast(grad, lista_dana[i]).getTemp()) < 10) {
                            temps[i].setText(dbHelper.readForecast(grad, lista_dana[i]).getTemp() + "°C");
                            hums[i].setText(dbHelper.readForecast(grad, lista_dana[i]).getHumidity());
                            presss[i].setText(dbHelper.readForecast(grad, lista_dana[i]).getPressure());
                            dani[i].setTypeface(null, Typeface.BOLD);

                        } else {
                            temps[i].setText("");
                            hums[i].setText("");
                            presss[i].setText("");
                            dani[i].setTypeface(null, Typeface.NORMAL);
                        }

                    } catch (CursorIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }



        });

        brisi_sredu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<7;i++){
                    if(lista_dana[i].equals("Sreda")){
                        temps[i].setText("");
                        presss[i].setText("");
                        hums[i].setText("");
                        dbHelper.deleteForecast(grad,lista_dana[i]);
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
