package pnrs.vezbe.projekat_1;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ime_grada;
    private StatisticsDbHelper dbHelper;
    TextView temp_uto;
    TextView press_uto;
    TextView hum_uto;
    String grad;



    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);
        dbHelper=new StatisticsDbHelper(this);

        findViewById(R.id.pon).setOnClickListener(this);
       // findViewById(R.id.uto).setOnClickListener(this);
        findViewById(R.id.sre).setOnClickListener(this);
        findViewById(R.id.cet).setOnClickListener(this);
        findViewById(R.id.pet).setOnClickListener(this);
        findViewById(R.id.sub).setOnClickListener(this);
        findViewById(R.id.ned).setOnClickListener(this);
        findViewById(R.id.slika1).setOnClickListener(this);
        findViewById(R.id.slika2).setOnClickListener(this);
        ime_grada=findViewById(R.id.ime_grada);
        temp_uto = findViewById(R.id.temp_uto);
        press_uto = findViewById(R.id.press_uto);
        hum_uto = findViewById(R.id.hum_uto);
        grad=getIntent().getStringExtra("grad");
        final String dan=getIntent().getStringExtra("dan");
        final String temp =getIntent().getStringExtra("temp");
        final String humidity = getIntent().getStringExtra("humidity");
        final String pressure = getIntent().getStringExtra("pressure");
        ime_grada.setText(grad);

        TextView uto=findViewById(R.id.uto);
        uto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp_uto.setText(temp);
                hum_uto.setText(humidity);
                press_uto.setText(pressure);
                temp_uto.setTextColor(Color.WHITE);

            }
        });




        Forecast forecast = new Forecast(dan,grad,temp,pressure,humidity);
        dbHelper.insert(forecast);
        forecast= dbHelper.readForecast(grad,dan);
       // Log.d("StatisticsActivity",temp);
       // Log.d("StatisticsActivity",pressure);





    }

    @Override
    public void onClick(View view) {



    }
}
