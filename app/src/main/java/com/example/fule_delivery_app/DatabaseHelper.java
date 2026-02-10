package com.example.fule_delivery_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "UserDatabase.db";
    public static final String TABLE_USER = "users";
    public static final String TABLE_MECHANIC = "mechanic";

    public static final String TABLE_DELIVERY = "delivery";


    // upload petrol pump

    public static final String TABLE_PETROLPUMP = "petrolpump";

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_ADDRESS = "address";

    public static final String COL_NUMBER = "number";

    public static final String COL_TIME = "time";

    public static final String COL_CHARGES = "charges";

    public static final String COL_LAT = "latitude";

    public static final String COL_LNG = "longitude";

    public static final String COL_IMAGE = "imageuri";


    // garage data

    private static final String TABLE_GARAGE = "garage";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_SERVICES = "services";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_LAT = "latitude";
    private static final String COLUMN_LNG = "longitude";


    // petrol request


    private static final String TABLE_PREQUEST = "prequest";

    // garage request

    private static final String TABLE_GREQUEST = "grequest";















    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // user table create table

        db.execSQL("CREATE TABLE " + TABLE_USER +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullname TEXT," +
                "address TEXT," +
                "number TEXT," +
                "username TEXT UNIQUE," +
                "password TEXT)");

        // mechanic table create table

        db.execSQL("CREATE TABLE " + TABLE_MECHANIC +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullname TEXT," +
                "address TEXT," +
                "number TEXT," +
                "username TEXT UNIQUE," +
                "password TEXT)");

        // delivery boy create table


        db.execSQL("CREATE TABLE " + TABLE_DELIVERY +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullname TEXT," +
                "address TEXT," +
                "number TEXT," +
                "username TEXT UNIQUE," +
                "password TEXT)");


        // create pretrol pump table

        db.execSQL("CREATE TABLE " + TABLE_PETROLPUMP + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT,"
                + COL_ADDRESS + " TEXT,"
                + COL_NUMBER + " TEXT,"
                + COL_TIME + " TEXT,"
                + COL_CHARGES + " TEXT,"
                + COL_LAT + " REAL,"
                + COL_LNG + " REAL,"
                + COL_IMAGE + " TEXT)");

        // garage table create

        String CREATE_TABLE = "CREATE TABLE " + TABLE_GARAGE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_NUMBER + " TEXT," +
                COLUMN_TIME + " TEXT," +
                COLUMN_SERVICES + " TEXT," +
                COLUMN_IMAGE + " TEXT," +
                COLUMN_LAT + " REAL," +
                COLUMN_LNG + " REAL)";
        db.execSQL(CREATE_TABLE);


        // petrol pump request

        db.execSQL("CREATE TABLE " + TABLE_PREQUEST +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, number TEXT, carNumber TEXT, type TEXT, " +
                "quantity INTEGER, latitude DOUBLE, longitude DOUBLE)");

        // garage request table create

        db.execSQL("CREATE TABLE " + TABLE_GREQUEST +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, number TEXT, carNumber TEXT, type TEXT, " +
                "latitude DOUBLE, longitude DOUBLE)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MECHANIC);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELIVERY);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PETROLPUMP);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GARAGE);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREQUEST);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GREQUEST);
        onCreate(db);



    }

    // user registration
    public boolean registerUser(String fullname, String address, String number, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("address", address);
        contentValues.put("number", number);
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = db.insert(TABLE_USER, null, contentValues);
        return result != -1;
    }

    // user login

    public boolean loginuser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USER + " WHERE username=? AND password=?",
                new String[]{username, password}
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    // user forgot password
    public boolean UserupdatePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPassword);
        int result = db.update(TABLE_USER, cv, "username=?", new String[]{username});
        return result > 0;
    }


    // mechanic register

    public boolean registerMechanic(String fullname, String address, String number, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("address", address);
        contentValues.put("number", number);
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = db.insert(TABLE_MECHANIC, null, contentValues);
        return result != -1;
    }

    // mechanic login

    public boolean loginmechanic(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_MECHANIC + " WHERE username=? AND password=?",
                new String[]{username, password}
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    // forgot password mechanic

    public boolean MechanicupdatePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPassword);
        int result = db.update(TABLE_MECHANIC, cv, "username=?", new String[]{username});
        return result > 0;
    }

    // delivery boy register

    public boolean registerDelivery(String fullname, String address, String number, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("address", address);
        contentValues.put("number", number);
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = db.insert(TABLE_DELIVERY, null, contentValues);
        return result != -1;
    }

    // delivery login

    public boolean logindelivery(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_DELIVERY + " WHERE username=? AND password=?",
                new String[]{username, password}
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // delivery forgot password
    public boolean DeliveryupdatePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPassword);
        int result = db.update(TABLE_DELIVERY, cv, "username=?", new String[]{username});
        return result > 0;
    }


    // upload petrol pump

    public long insertPetrolpump(Petrolpump petrolpump) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_NAME, petrolpump.getName());
        cv.put(COL_ADDRESS, petrolpump.getAddress());
        cv.put(COL_NUMBER, petrolpump.getNumber());
        cv.put(COL_TIME, petrolpump.getTime());
        cv.put(COL_CHARGES, petrolpump.getCharges());
        cv.put(COL_LAT, petrolpump.getLatitude());
        cv.put(COL_LNG, petrolpump.getLongitude());
        cv.put(COL_IMAGE, petrolpump.getImageuri()); // image stored as String URI

        long id = db.insert(TABLE_PETROLPUMP, null, cv);
        db.close();
        return id;
    }


    // get all petrolpump

    public List<Petrolpump> getAllPetrolpumps() {
        List<Petrolpump> petrolpumpList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PETROLPUMP, null);

        if (cursor.moveToFirst()) {
            do {
                Petrolpump petrolpump = new Petrolpump();

               // petrolpump.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
                petrolpump.setName(cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)));
                petrolpump.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS)));
                petrolpump.setNumber(cursor.getString(cursor.getColumnIndexOrThrow(COL_NUMBER)));
                petrolpump.setTime(cursor.getString(cursor.getColumnIndexOrThrow(COL_TIME)));
                petrolpump.setCharges(cursor.getString(cursor.getColumnIndexOrThrow(COL_CHARGES)));
                petrolpump.setLatitude(cursor.getDouble(cursor.getColumnIndexOrThrow(COL_LAT)));
                petrolpump.setLongitude(cursor.getDouble(cursor.getColumnIndexOrThrow(COL_LNG)));
                petrolpump.setImageuri(cursor.getString(cursor.getColumnIndexOrThrow(COL_IMAGE)));

                petrolpumpList.add(petrolpump);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return petrolpumpList;
    }

    // upload garage data

    public long insertGarage(Garage garage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, garage.getName());
        cv.put(COLUMN_ADDRESS, garage.getAddress());
        cv.put(COLUMN_NUMBER, garage.getNumber());
        cv.put(COLUMN_TIME, garage.getTime());
        cv.put(COLUMN_SERVICES, garage.getServices());
        cv.put(COLUMN_LAT, garage.getLatitude());
        cv.put(COLUMN_LNG, garage.getLongitude());
        cv.put(COLUMN_IMAGE, garage.getImageUri());

        long res = db.insert(TABLE_GARAGE, null, cv);
        db.close();
        return res;
    }


    // get all garage data

    public List<Garage> getAllGarages() {
        List<Garage> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GARAGE, null);

        if (cursor.moveToFirst()) {
            do {
                Garage garage = new Garage();
//                garage.setId(cursor.getInt(0));
                garage.setName(cursor.getString(1));
                garage.setAddress(cursor.getString(2));
                garage.setNumber(cursor.getString(3));
                garage.setTime(cursor.getString(4));
                garage.setServices(cursor.getString(5));
                garage.setImageUri(cursor.getString(6));
                garage.setLatitude(cursor.getDouble(7));
                garage.setLongitude(cursor.getDouble(8));

                list.add(garage);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }


    // upload petrol request


    public boolean insertpetrolrequest(PetrolRequest user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", user.name);
        cv.put("number", user.number);
        cv.put("carNumber", user.carNumber);
        cv.put("type", user.type);
        cv.put("quantity", user.quantity);
        cv.put("latitude", user.latitude);
        cv.put("longitude", user.longitude);

        long result = db.insert(TABLE_PREQUEST, null, cv);
        return result != -1;
    }


    // get all petrol request

    public ArrayList<PetrolRequest> getAllPREQUEST() {
        ArrayList<PetrolRequest> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PREQUEST, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new PetrolRequest(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getDouble(6),
                        cursor.getDouble(7)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // upload garage request

    public boolean insertgaragerequest(GarajeRequest user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", user.name);
        cv.put("number", user.number);
        cv.put("carNumber", user.carNumber);
        cv.put("type", user.type);
        cv.put("latitude", user.latitude);
        cv.put("longitude", user.longitude);

        long result = db.insert(TABLE_GREQUEST, null, cv);
        return result != -1;
    }

    // get all garaje request

    public ArrayList<GarajeRequest> getAllREQUEST() {
        ArrayList<GarajeRequest> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GREQUEST, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new GarajeRequest(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getDouble(5),
                        cursor.getDouble(6)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }


}
