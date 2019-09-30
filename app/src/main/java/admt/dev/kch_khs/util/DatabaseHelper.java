package admt.dev.kch_khs.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import static android.support.constraint.Constraints.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {



    private static final String Tag = "DatabaseHelper";
    private static final String TABLE_NAME = "fnotification";
    private static final String col0 = "id";
    private static final String col1 = "title";
    private static final String col2 = "body";



    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);



    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY  AUTOINCREMENT, "  + col1 + " TEXT )";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
            onCreate(db);
    }


    public boolean addData(String item){

   SQLiteDatabase db = this.getWritableDatabase();
   ContentValues contentValues = new ContentValues();
   contentValues.put(col1, item);

   Log.d(TAG, "addData" + item + "to" + TABLE_NAME);
   long result = db.insert(TABLE_NAME, null, contentValues);

   if(result == -1){

       return false;
   }else{

       return true;
   }

    }



    public Cursor getData(){

    SQLiteDatabase db = this.getWritableDatabase();
    String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY title ASC";
    Cursor data = db.rawQuery(query,null);
    return data;



    }





}
