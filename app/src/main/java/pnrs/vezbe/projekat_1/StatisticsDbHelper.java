package pnrs.vezbe.projekat_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class StatisticsDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "weather.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "WeatherForecast";
    public static final String DATE = "date";
    public static final String CITY_NAME = "cityname";
    public static final String PRESSURE = "pressure";
    public static final String HUMIDITY = "humidity";
    public static final String TEMP = "temperature";

    public StatisticsDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                DATE + " TEXT, " +
                CITY_NAME + " TEXT, " +
                TEMP + " TEXT, " +
                PRESSURE + " TEXT, " +
                HUMIDITY + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(Forecast forecast) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE, forecast.getDan());
        values.put(CITY_NAME, forecast.getGrad());
        values.put(TEMP, forecast.getTemp());
        values.put(PRESSURE, forecast.getPressure());
        values.put(HUMIDITY, forecast.getHumidity());
        db.insert(TABLE_NAME, null, values);
        close();
    }

    public Forecast readForecast(String grad,String dan){
            SQLiteDatabase database= getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, CITY_NAME+"=? AND " +DATE+"=?", new String[] {grad,dan}, null, null, null);
        cursor.moveToFirst();
        Forecast forecast = createForecast(cursor);
        close();
        return forecast;

    }
    public Forecast findForecast(String grad,String temp){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,null,CITY_NAME+"=? AND "+TEMP+"=?",new String[]{grad,temp},null,null,null);
        cursor.moveToFirst();
        Forecast forecast = createForecast(cursor);
        close();
        return forecast;
    }
    public Forecast[] readForecasts(String grad) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, CITY_NAME+"=?", new String[] {grad}, null, null, null, null);

        if(cursor.getCount() <= 0){
            return null;
        }
        Forecast[] forecasts = new Forecast[cursor.getCount()];
        int i=0;
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            forecasts[i++] = createForecast(cursor);
        }
        close();
        return forecasts;

    }

    private Forecast createForecast(Cursor cursor){
        String temp = cursor.getString(cursor.getColumnIndex(TEMP));
        String pressure = cursor.getString(cursor.getColumnIndex(PRESSURE));
        String humidity = cursor.getString(cursor.getColumnIndex(HUMIDITY));
        String dan=cursor.getString(cursor.getColumnIndex(DATE));
        String grad = cursor.getString(cursor.getColumnIndex(CITY_NAME));
        return new Forecast(dan,grad,temp,pressure,humidity);
    }
    public void deleteForecast(String grad,String dan) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, CITY_NAME+"=? AND " +DATE+"=?", new String[] {grad,dan});
        close();
    }
    public void deleteCity(String grad){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, CITY_NAME+"=?", new String[] {grad});
        close();

    }
}
