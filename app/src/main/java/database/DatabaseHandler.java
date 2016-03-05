package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import objects.Vehicle;

/**
 * Created by Thisaru on 3/5/2016.
 * Thw data base Handler claas.
 * Add / Insert Records
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "vehicle_servey";

    private static final String TABLE_NAME = "vehical_details";

    // Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "vehicle type";
    private static final String KEY_TIME = "time";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TYPE + " INTEGER,"
                + KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void addVehicle (Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, vehicle.getVehicleType().getType()); // Vehicle Type (String) from the ENUM
        values.put(KEY_TIME, vehicle.getTimeStamp().toString().substring(0, 19)); // Time Stamp in "yyyy-MM-dd HH:mm:ss" format
        // Substring to remove nano seconds part

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public List<Vehicle> getAllVehicles () {
        List<Vehicle> vehicleList = new ArrayList<>();

        String selectQuery = "Select * from " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Vehicle vehicle = new Vehicle();

            } while (cursor.moveToNext());
        }

        cursor.close();
        return vehicleList;
    }
}
