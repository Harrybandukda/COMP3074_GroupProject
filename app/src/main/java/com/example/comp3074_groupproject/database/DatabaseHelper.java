package com.example.comp3074_groupproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_PROFILE_PIC = "profile_pic";
    private static final String COLUMN_USER_HEIGHT_FT = "height_ft";
    private static final String COLUMN_USER_HEIGHT_IN = "height_in";
    private static final String COLUMN_USER_WEIGHT = "weight";
    private static final String COLUMN_USER_ACTIVITY_LVL = "activity_lvl";
    private static final String COLUMN_USER_ACTIVITY_LVL_POSITION = "activity_lvl_position";
    private static final String COLUMN_USER_YEAR_JOINED = "year_joined";

    private static final String TABLE_DIET_PREF = "diet_pref";
    private static final String COLUMN_DIET_PREF_ID = "id";
    private static final String COLUMN_DIET_PREF_USER_ID = "user_id";
    private static final String COLUMN_DIET_PREF_PREFERENCE = "preference";

    private static final String TABLE_ALLERGIES = "allergies";
    private static final String COLUMN_ALLERGIES_ID = "id";
    private static final String COLUMN_ALLERGIES_USER_ID = "user_id";
    private static final String COLUMN_ALLERGIES_ALLERGY = "allergy";


    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + "("
                    + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USER_NAME + " TEXT,"
                    + COLUMN_USER_PROFILE_PIC + " TEXT,"
                    + COLUMN_USER_HEIGHT_FT + " REAL,"
                    + COLUMN_USER_HEIGHT_IN + " REAL,"
                    + COLUMN_USER_WEIGHT + " REAL,"
                    + COLUMN_USER_ACTIVITY_LVL + " TEXT,"
                    + COLUMN_USER_ACTIVITY_LVL_POSITION + " INTEGER,"
                    + COLUMN_USER_YEAR_JOINED + " INTEGER" + ")";

    private static final String CREATE_TABLE_DIET_PREF =
            "CREATE TABLE " + TABLE_DIET_PREF + "("
                    + COLUMN_DIET_PREF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DIET_PREF_USER_ID + " INTEGER,"
                    + COLUMN_DIET_PREF_PREFERENCE + " TEXT,"
                    + "FOREIGN KEY(" + COLUMN_DIET_PREF_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + ")" + ")";

    private static final String CREATE_TABLE_ALLERGIES =
            "CREATE TABLE " + TABLE_ALLERGIES + "("
                    + COLUMN_ALLERGIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ALLERGIES_USER_ID + " INTEGER,"
                    + COLUMN_ALLERGIES_ALLERGY + " TEXT,"
                    + "FOREIGN KEY(" + COLUMN_ALLERGIES_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + ")" + ")";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_DIET_PREF);
        db.execSQL(CREATE_TABLE_ALLERGIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIET_PREF );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLERGIES );

        onCreate(db);
    }


    // Insert Initial Data
    public void insertInitialData(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, "Nicole Milmine");
        values.put(COLUMN_USER_PROFILE_PIC, "");
        values.put(COLUMN_USER_HEIGHT_FT, 5);
        values.put(COLUMN_USER_HEIGHT_IN, 10);
        values.put(COLUMN_USER_WEIGHT, 150);
        values.put(COLUMN_USER_ACTIVITY_LVL, "Moderate");
        values.put(COLUMN_USER_ACTIVITY_LVL_POSITION, 1);
        values.put(COLUMN_USER_YEAR_JOINED, 2024);

        db.insert(TABLE_USERS, null, values);

        values = new ContentValues();
        values.put(COLUMN_DIET_PREF_USER_ID, 1);
        values.put(COLUMN_DIET_PREF_PREFERENCE, "Vegetarian");

        db.insert(TABLE_DIET_PREF, null, values);

        values = new ContentValues();
        values.put(COLUMN_DIET_PREF_USER_ID, 1);
        values.put(COLUMN_DIET_PREF_PREFERENCE, "Low Carb");

        db.insert(TABLE_DIET_PREF, null, values);

        values = new ContentValues();
        values.put(COLUMN_DIET_PREF_USER_ID, 1);
        values.put(COLUMN_DIET_PREF_PREFERENCE, "Gluten-Free");

        db.insert(TABLE_DIET_PREF, null, values);

        values = new ContentValues();
        values.put(COLUMN_ALLERGIES_USER_ID, 1);
        values.put(COLUMN_ALLERGIES_ALLERGY, "Dairy");

        db.insert(TABLE_ALLERGIES, null, values);

        db.close();
    }


    // Add a user
    public void addUser(String name,
                        String profilePic,
                        int heightFt,
                        int heightIn,
                        float weight,
                        String activityLvl,
                        int activityLvlPosition,
                        int yearJoined) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_PROFILE_PIC, profilePic);
        values.put(COLUMN_USER_HEIGHT_FT, heightFt);
        values.put(COLUMN_USER_HEIGHT_IN, heightIn);
        values.put(COLUMN_USER_WEIGHT, weight);
        values.put(COLUMN_USER_ACTIVITY_LVL, activityLvl);
        values.put(COLUMN_USER_ACTIVITY_LVL_POSITION, activityLvlPosition);
        values.put(COLUMN_USER_YEAR_JOINED, yearJoined);

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    // Update a user
    public int updateUser(int userId, String name, int heightFt, int heightIn, float weight, String activityLvl, int activityLvlPosition, int yearJoined) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_HEIGHT_FT, heightFt);
        values.put(COLUMN_USER_HEIGHT_IN, heightIn);
        values.put(COLUMN_USER_WEIGHT, weight);
        values.put(COLUMN_USER_ACTIVITY_LVL, activityLvl);
        values.put(COLUMN_USER_ACTIVITY_LVL_POSITION, activityLvlPosition);
        values.put(COLUMN_USER_YEAR_JOINED, yearJoined);


        return db.update(TABLE_USERS, values, COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
    }

    // Delete a user
    public void deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
        db.close();
    }

    // Get user by id
    public User getUserById(int userId){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = ? ";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor != null && cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(COLUMN_USER_ID);
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_USER_NAME);
            int heightFtColumnIndex = cursor.getColumnIndex(COLUMN_USER_HEIGHT_FT);
            int heightInColumnIndex = cursor.getColumnIndex(COLUMN_USER_HEIGHT_IN);
            int weightColumnIndex = cursor.getColumnIndex(COLUMN_USER_WEIGHT);
            int activityLvlColumnIndex = cursor.getColumnIndex(COLUMN_USER_ACTIVITY_LVL);
            int activityLvlPositionColumnIndex = cursor.getColumnIndex(COLUMN_USER_ACTIVITY_LVL_POSITION);
            int yearJoinedColumnIndex = cursor.getColumnIndex(COLUMN_USER_YEAR_JOINED);

            if (idColumnIndex != -1 && nameColumnIndex != -1 &&
                    heightFtColumnIndex != -1 &&
                    heightInColumnIndex != -1 &&
                    weightColumnIndex != -1 &&
                    activityLvlColumnIndex != -1 &&
                    activityLvlPositionColumnIndex != -1 &&
                    yearJoinedColumnIndex != -1) {

                User user = new User(
                        cursor.getInt(idColumnIndex),
                        cursor.getString(nameColumnIndex),
                        cursor.getInt(heightFtColumnIndex),
                        cursor.getInt(heightInColumnIndex),
                        cursor.getLong(weightColumnIndex),
                        cursor.getString(activityLvlColumnIndex),
                        cursor.getInt(activityLvlPositionColumnIndex),
                        cursor.getInt(yearJoinedColumnIndex)
                );
                cursor.close();
                return user;
            }
        }

        cursor.close();
        return null;
    }



    // Add a diet preference
    public void addDietPreference(int userId, String preference) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DIET_PREF_USER_ID, userId);
        values.put(COLUMN_DIET_PREF_PREFERENCE, preference);

        db.insert(TABLE_DIET_PREF, null, values);
        db.close();
    }

    // Delete a diet preference
    public void deleteDietPreference(int dietPrefId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DIET_PREF, COLUMN_DIET_PREF_ID + "=?", new String[]{String.valueOf(dietPrefId)});
        db.close();
    }

    // Get Diet prefs by userId
    public String[] getDietPrefByUserId(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> preferencesList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_DIET_PREF;
        Cursor cursor = db.rawQuery(query, new String[0]);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                if(cursor.getInt(1) == userId){
                    preferencesList.add(cursor.getString(2));
                }
            } while (cursor.moveToNext());
        } else {
            return null;
        }

        cursor.close();
        return preferencesList.toArray(new String[0]);
    }



    // Add an allergy
    public void addAllergy(int userId, String allergy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ALLERGIES_USER_ID, userId);
        values.put(COLUMN_ALLERGIES_ALLERGY, allergy);

        long allergyId = db.insert(TABLE_ALLERGIES, null, values);
        db.close();
    }

    // Delete an allergy
    public void deleteAllergy(int allergyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALLERGIES, COLUMN_ALLERGIES_ID + "=?", new String[]{String.valueOf(allergyId)});
        db.close();
    }

    // Get allergies by user id
    public String[] getAllergiesByUserId(int userId){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> allergiesList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_ALLERGIES;
        Cursor cursor = db.rawQuery(query, new String[0]);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                if(cursor.getInt(1) == userId){
                    allergiesList.add(cursor.getString(2));
                }
            } while (cursor.moveToNext());
        } else {
            return null;
        }

        cursor.close();
        return allergiesList.toArray(new String[0]);
    }

}
