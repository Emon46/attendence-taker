package com.example.android.attendencetakerfinal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class termtestInput extends AppCompatActivity {



    DbHelper myDbHelper;
    ListView listView;
    public String currentTableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termtest_input);

        myDbHelper=new DbHelper(this);

        listView=(ListView)findViewById(R.id.termtest_resultshow);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            currentTableName=bundle.getString("TableName").trim();
            loadData(currentTableName);


        }
    }


    private void loadData(String openTableName )
    {
        ArrayList<termtestHelper> ListData=new ArrayList<>();
        final Cursor cursor=myDbHelper.showStudentInformation(openTableName);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                termtestHelper mtermtesthelper=new termtestHelper(cursor.getString(2),
                        cursor.getString(6),cursor.getString(7));

                ListData.add(mtermtesthelper);
            }
        }
        final termtestArrayAdapter  adapter= (termtestArrayAdapter) new termtestArrayAdapter(
                currentTableName,this,
                R.layout.termtestexample,
                ListData );
        listView.setAdapter(adapter);
        cursor.close();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Cursor cursor=myDbHelper.showStudentInformation(currentTableName);

        return true;
    }
}
