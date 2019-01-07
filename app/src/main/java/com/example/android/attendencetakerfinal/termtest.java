package com.example.android.attendencetakerfinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class termtest extends AppCompatActivity {
    DbHelper newDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termtest);
        newDbHelper=new DbHelper(this);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        displayTableInfo();
    }

    private void displayTableInfo() {
        SQLiteDatabase db=newDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        final ArrayList<String> tableName;
        tableName = new ArrayList<String>();

        if (cursor.getCount()>0) {
            //to avoid android_metadata
            cursor.moveToNext();
            int pos=0;

            while ( cursor.moveToNext() ) {
                pos++;
                //to avoid android metadata
                if(pos!=2) {
                    tableName.add(cursor.getString(0));
                }
            }
        }
        ListView listView=(ListView)findViewById(R.id.TableListedtermtest);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                R.layout.table_listed_front,R.id.TableNameOfListed,tableName);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Table=tableName.get(position).toString().trim();
                Intent intent=new Intent(termtest.this,termtestInput.class);

                intent.putExtra("TableName",Table);
                startActivity(intent);
            }
        });
        cursor.close();
    }
}
