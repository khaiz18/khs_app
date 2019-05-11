package admt.dev.kch_khs.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.util.ArrayList;

import admt.dev.kch_khs.R;
import admt.dev.kch_khs.util.DatabaseHelper;

public class fbell extends AppCompatActivity implements View.OnClickListener{



    DatabaseHelper mDatabaseHelper;

    ImageView img_home_back;
    Button imgbtnDelete;


    public ListView mlistview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbell_notifications);

        mlistview = (ListView) findViewById(R.id.fbell_list);
        mDatabaseHelper = new DatabaseHelper(this);

        img_home_back = (ImageView) findViewById(admt.dev.kch_khs.R.id.img_home_back_clubs);
        img_home_back.setOnClickListener(this);

        imgbtnDelete = (Button) findViewById(R.id.imgbtnDelete);
        imgbtnDelete.setOnClickListener(this);

        populateListView();

    }

    private void populateListView(){

        Log.d("populateListView", "Displaying data in list view");

        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){

            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mlistview.setAdapter(adapter);
    }




    @Override
    public void onClick(View v) {

         switch(v.getId()){

             case R.id.imgbtnDelete:


                 SQLiteDatabase db = mDatabaseHelper.getWritableDatabase(); //get database
                 db.execSQL("DELETE FROM fnotification"); //delete all rows in a table
                 db.close();
                 finish();
                 break;

             case admt.dev.kch_khs.R.id.img_home_back_clubs:
                 finish();
                 break;




         }

    }
}
