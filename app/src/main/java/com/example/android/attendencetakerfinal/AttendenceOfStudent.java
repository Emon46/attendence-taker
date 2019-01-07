package com.example.android.attendencetakerfinal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.attendencetakerfinal.studentContract.headerEntry;

import java.util.ArrayList;

public class AttendenceOfStudent extends AppCompatActivity {
    DbHelper myDbHelper;
    ListView listView;
    public String currentTableName;
    public static int PRESENTALL=0;
     AttendenceArrayAdapter adapter;

    static ArrayList<Integer> ispresent=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        getSupportActionBar().setTitle("Student List");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_of_student);
        myDbHelper=new DbHelper(this);

        listView=(ListView)findViewById(R.id.attendence_list);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            currentTableName=bundle.getString("TableName").trim();
            loadData(currentTableName);


        }
      Button starting=(Button) findViewById(R.id.savingpresent);
        starting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor=myDbHelper.showStudentInformation(currentTableName);


            }
        });

    }

    public void loadData(String openTableName )
    {
        ArrayList<attendenceHelper> ListData=new ArrayList<>();
        final Cursor cursor=myDbHelper.showStudentInformation(openTableName);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                ispresent.add(0);
                attendenceHelper attendenceHelper=new attendenceHelper(cursor.getString(1),
                        cursor.getString(2));

                ListData.add(attendenceHelper);
            }
        }
        adapter = (AttendenceArrayAdapter) new AttendenceArrayAdapter(
                currentTableName,this,
                R.layout.attendence_shit_example,
                ListData );
        listView.setAdapter(adapter);

    }
    public boolean update(int i)
    {
        SQLiteDatabase db=myDbHelper.getWritableDatabase();
        Cursor cursor=myDbHelper.showStudentInformation(currentTableName);
        cursor.moveToPosition(i);
        String reg=cursor.getString(2);
        String total=cursor.getString(4);
        int totalint=Integer.parseInt(total);

        String present=cursor.getString(5);
        int presentint=Integer.parseInt(present);


        ContentValues value=new ContentValues();
        value.put(headerEntry.headerNAME,cursor.getString(1));
        value.put(headerEntry.headerReg,cursor.getString(2));
        value.put(headerEntry.headerTotalClass,totalint+1);
        value.put(headerEntry.headerPresentTotal,presentint
                +  ispresent.get(i));
        value.put(headerEntry.headerPreviousday,ispresent.get(i));
        db.update(currentTableName,value,"Reg = ?",new String[] {reg});
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Cursor cursor=myDbHelper.showStudentInformation(currentTableName);
        switch (item.getItemId()){
            case R.id.save:
                for(int i=0;i<cursor.getCount();i++)
                {
                    boolean isworking= update(i);
                    ispresent.set(i,0);
                    if(isworking)
                    {

                        Toast.makeText(getApplicationContext(),currentTableName,Toast.LENGTH_LONG).show();
                    }

                }
                break;

            case R.id.presentall:
                PRESENTALL=1;
                adapter.presentAll();
                break;

            case R.id.absentall:
                adapter.absentAll();
                break;

        }
        return true;
    }
}
