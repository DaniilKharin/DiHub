package com.danii.dihub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

        // Database Version
        private static final int DATABASE_VERSION = 1;
        // Database Name
        private static final String DATABASE_NAME = "dbrepos";
        // Contacts table name
        private static final String TABLE = "repos";


        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "create table repos ("
                    + " id integer primary key autoincrement,"
                    + " type text,"
                    + " username text,"
                    + " res type text,"
                    + " name text,"
                    + " fullname text,"
                    + " owner text,"
                    + " htmlUrl text,"
                    + " description text,"
                    + " createdAt text,"
                    + " updatedAt text,"
                    + " stargazersCount integer,"
                    + " language text,"
                    + " date integer "
                    + ");";
            db.execSQL(CREATE_CONTACTS_TABLE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE);

            onCreate(db);
        }





    public void addRepo(GithubRepo r,String username,final String reptypes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("name",r.getName().toString());
        cv.put("type",reptypes.toString());
        cv.put("fullname",r.getFullName().toString());
        cv.put("owner",r.getOwner().getLogin());
        cv.put("htmlUrl",r.getHtmlUrl().toString());
        cv.put("description",r.getDescription().toString());
        cv.put("createdAt",r.getCreatedAt().toString());
        cv.put("updatedAt",r.getUpdatedAt().toString());
        cv.put("stargazersCount",r.getStargazersCount().toString());
        cv.put("language",r.getLanguage().toString());
        Calendar calendar = Calendar.getInstance();
        cv.put("date",String.valueOf(calendar.getTimeInMillis()));
        int id= (int) db.insert("repos", null, cv);
        db.close(); // Closing database connection
    }

    public List<GithubRepo> getAllRepo(String username, final String repotype) {
        List<GithubRepo> result = new ArrayList<GithubRepo>();

        String selectQuery = "SELECT * FROM repos WHERE username = ? AND type = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[]{username,repotype});
        // определяем номера столбцов по имени в выборке
        int nameColIndex = c.getColumnIndex("name");
        int fullnameColIndex = c.getColumnIndex("fullname");
        int ownerColIndex = c.getColumnIndex("owner");
        int htmlUrlColIndex = c.getColumnIndex("htmlUrl");
        int descriptionColIndex = c.getColumnIndex("description");
        int createdAtColIndex = c.getColumnIndex("createdAt");
        int updatedAtColIndex = c.getColumnIndex("updatedAt");
        int stargazersCountColIndex = c.getColumnIndex("stargazersCount");
        int languageColIndex = c.getColumnIndex("language");
        
      if (c.moveToFirst()) {
            do {
                GithubRepo r = new GithubRepo();
                Owner owner = new Owner();
                r.setName(c.getString(nameColIndex));
                r.setFullName(c.getString(fullnameColIndex));
                owner.setLogin(c.getString(ownerColIndex));
                r.setOwner(owner);
                r.setHtmlUrl(c.getString(htmlUrlColIndex));
                r.setDescription(c.getString(descriptionColIndex));
                r.setCreatedAt(c.getString(createdAtColIndex));
                r.setUpdatedAt(c.getString(updatedAtColIndex));
                r.setStargazersCount(c.getInt(stargazersCountColIndex));
                r.setLanguage(c.getString(languageColIndex));
                result.add(r);
            } while (c.moveToNext());
          return result;
        }
        else return null;

    }
    public void clearOld() {
        Calendar calendar = Calendar.getInstance();
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("repos","date > ?",new String[]{String.valueOf(calendar.getTimeInMillis()-300000)});
    }

}