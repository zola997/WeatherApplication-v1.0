package pnrs.vezbe.projekat_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DateDb1 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "date1.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME1 = "datum";
    public static final String DATUM = "datum";
    public static final String GRAD = "grad";

    public DateDb1(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME1 + " (" +
                GRAD + " TEXT, " +
                DATUM +" TEXT);");

    }
    public void insertDate(String date,String grad){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GRAD,grad);
        values.put(DATUM,date);
        db.insert(TABLE_NAME1,null,values);
        close();

    }
    public String readLastDate(String grad){
        SQLiteDatabase database= getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME1, null, GRAD + "=?", new String[] {grad}, null, null, null);
        cursor.moveToFirst();
        String datum=cursor.getString(cursor.getColumnIndex(DATUM));
        close();
        return datum;

    }
    public void deleteOldDate(String grad){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME1, GRAD + "=?", new String[] {grad});
        close();

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
