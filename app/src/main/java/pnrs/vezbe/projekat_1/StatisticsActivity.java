package pnrs.vezbe.projekat_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;


public class StatisticsActivity extends SQLiteOpenHelper implements View.OnClickListener {
    public static final String DATABASE_NAME = "weather.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "WeatherForecast";
    public static final String DATE = "date";
    public static final String CITY_NAME = "cityname";
    public static final String PRESSURE = "pressure";
    public static final String HUMIDITY = "humidity";
    public static final String SUNRISE = "sunrise";
    public static final String SUNSET = "sunset";
    public static final String WIND_SPEED = "windspeed";
    public static final String WIND_DIR = "winddir";
    public static final String TEMP = "temperature";

    public StatisticsActivity(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                DATE + " TEXT, " +
                CITY_NAME + " TEXT, " +
                TEMP + " TEXT, " +
                PRESSURE + " TEXT, " +
                HUMIDITY + " TEXT, " +
                SUNRISE + " TEXT, " +
                SUNSET + " TEXT, " +
                WIND_SPEED + " TEXT, " +
                WIND_DIR + " TEXT);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onClick(View view) {

    }
}
