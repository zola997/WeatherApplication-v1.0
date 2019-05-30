package pnrs.vezbe.projekat_1;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
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
import java.util.Date;


public class BoundService extends Service {
    private static final long PERIOD = 5*1000L;
    private BoundServiceExample mBounderExample = null;
    private final IBinder mBinder = new LocalBinder();
    private boolean serviceActive;
    private static String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static String API_KEY = "&APPID=6db369e4771614a635375634c3e40b8d";
    public static double celzijus,farenhajt;
    public SimpleDateFormat sdf;
    private Http httpHelper;
    public LinearLayout layout2,layout3,layout4;
    public Button temp,sunce,vetar_button,statistika;
    public String temp_kelvin,pressure,humidity;
    public ImageView slika,refresh;
    public String filename,grad,formattedDate;
    private StatisticsDbHelper dbHelper;
    private Forecast forecast;

    public BoundService() {
    }

    public class LocalBinder extends Binder {
        BoundService getService() {
            // Return this instance of BoundService so clients can call public methods
            return BoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate(){
        if (mBounderExample == null) {
            mBounderExample = new BoundServiceExample();
        }
        super.onCreate();
        mBounderExample.start();
        serviceActive = true;
        httpHelper=new Http();
        dbHelper=new StatisticsDbHelper(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBounderExample.stop();
        serviceActive = false;
    }
    public void start(){
        serviceActive = true;
        mBounderExample.start();
    }
    public void stop(){
        serviceActive = false;
        mBounderExample.stop();
    }
    public boolean getServiceStatus(){
        return serviceActive;
    }


    private class BoundServiceExample implements Runnable {
        private Handler mHandler;
        private boolean mRun = false;

        public BoundServiceExample() {
            mHandler = new Handler(getMainLooper());
        }

        public void start() {
            mRun = true;
            mHandler.postDelayed(this, PERIOD);
        }

        public void stop() {
            mRun = false;
            mHandler.removeCallbacks(this);
        }

        @Override
        public void run() {
            if (!mRun) {
                return;
            }

            //temp.getDataFromInternet();
            new Thread(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                        try {
                            final String URL = API_URL + DetailsActivity.grad + API_KEY;
                            JSONObject jsonObject = httpHelper.getJSONObjectFromURL(URL);

                            if (jsonObject == null) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("error", "Unable to connect.");
                                getApplicationContext().startActivity(intent);

                            } else {

                                JSONObject wind = (JSONObject) jsonObject.get("wind");
                                JSONObject main = (JSONObject) jsonObject.get("main");
                                NumberFormat form = new DecimalFormat("#0.0");
                                temp_kelvin = main.getString("temp");
                                celzijus = Double.parseDouble(temp_kelvin) - 273.15;
                                farenhajt = celzijus * 9 / 5 + 32;
                                pressure = main.getString("pressure");
                                humidity = main.getString("humidity");

                                dbHelper.deleteForecast(DetailsActivity.grad,DetailsActivity.getToday());
                                dbHelper.insert(new Forecast(DetailsActivity.getToday(),DetailsActivity.grad,String.valueOf(temp_kelvin),pressure,humidity));



                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


            }).start();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(BoundService.this, MainActivity.CHANNEL_ID);
            NumberFormat form = new DecimalFormat("#0.0");
            builder.setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setTicker("Weather")
                    .setSmallIcon(R.drawable.refresh)
                    .setContentTitle("Temperatura azurirana")
                    .setContentText(DetailsActivity.grad+" " + String.valueOf(celzijus) + " °C")
                    .setContentInfo("info")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager notificationManager = (NotificationManager) BoundService.this.getSystemService(Context.NOTIFICATION_SERVICE);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, builder.build());

            Log.d("test", "Hello from Runnable");
            mHandler.postDelayed(this, PERIOD);
        }
    }
}


