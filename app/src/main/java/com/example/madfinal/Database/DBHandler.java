package com.example.madfinal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

//Create a database using an SQL helper
public class DBHandler extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "database.db";

        public DBHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }



    public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        //create table
        private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                    UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                    UserProfile.Users.COLUMN_1 + " username," +
                    UserProfile.Users.COLUMN_2 + " password," +
                    UserProfile.Users.COLUMN_3 + " DateOfBirth," +
                    UserProfile.Users.COLUMN_4 + " gender)" ;

        //delete table
        private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;

        //Put information into a database
        public long addInfo(String username, String password, String dob, String gender){
            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(UserProfile.Users.COLUMN_1, username);
            values.put(UserProfile.Users.COLUMN_2, password);
            values.put(UserProfile.Users.COLUMN_3, dob);
            values.put(UserProfile.Users.COLUMN_4, gender);

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);

            return newRowId;
        }

        //Update a database
        public Boolean updateInfo (String username, String password, String dob, String gender){
            SQLiteDatabase db = getWritableDatabase();

            // New value for one column
            String title = "MyNewTitle";
            ContentValues values = new ContentValues();
            values.put(UserProfile.Users.COLUMN_2, password);
            values.put(UserProfile.Users.COLUMN_3, dob);
            values.put(UserProfile.Users.COLUMN_4, gender);

            // Which row to update, based on the title
            String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
            String[] selectionArgs = { username };

            int count = db.update(
                    UserProfile.Users.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);

            //condition
            if (count>=1){
                return true;
            }
            else{
                return false;
            }
        }

        //Delete information from a database
        public void deleteInfo(String username){
            //if u want to change the data inside the database u must get writabledb object
            SQLiteDatabase db = getWritableDatabase();

            // Define 'where' part of query.
            String selection = UserProfile.Users.COLUMN_1 + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { username };
            // Issue SQL statement.
            int deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);
        }

        //Read all information from a database
        public List readAllInfo(){

            String username = "Avinash";
            //if u want to read the data inside the database u must get readabledb object
            SQLiteDatabase db = getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                    UserProfile.Users.COLUMN_1,
                    UserProfile.Users.COLUMN_2,
                    UserProfile.Users.COLUMN_3,
                    UserProfile.Users.COLUMN_4
            };

            // Filter results WHERE "title" = 'My Title'
            String selection = UserProfile.Users.COLUMN_1 + " = ?";
            String[] selectionArgs = { username };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    UserProfile.Users.COLUMN_1 + " ASC";

            Cursor cursor = db.query(
                    UserProfile.Users.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            List usernames = new ArrayList<>();
            while(cursor.moveToNext()) {
                String user = cursor.getString(
                        cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
                usernames.add(user);
            }
            cursor.close();
            return usernames;
        }

    //Read all information from a database by id
    public List readAllInfo(String username){

        //if u want to read the data inside the database u must get readabledb object
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_2,
                UserProfile.Users.COLUMN_3,
                UserProfile.Users.COLUMN_4
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.COLUMN_1 + " = ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List userInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_2));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_3));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_4));
            userInfo.add(user);//0
            userInfo.add(password);//1
            userInfo.add(dob);//2
            userInfo.add(gender);//3
        }
        cursor.close();
        return userInfo;
    }

}
