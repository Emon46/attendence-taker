package com.example.android.attendencetakerfinal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class reportShow extends AppCompatActivity {

    DbHelper myDbHelper;
    ListView listView;
    public String currentTableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_show);

        myDbHelper=new DbHelper(this);

        listView=(ListView)findViewById(R.id.reportofAllStudent);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            currentTableName=bundle.getString("TableName").trim();
            loadData(currentTableName);


        }
    }


    private void loadData(String openTableName )
    {
        ArrayList<reportHelper> ListData=new ArrayList<>();
        final Cursor cursor=myDbHelper.showStudentInformation(openTableName);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                reportHelper mreportHelper=new reportHelper(cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getString(5));

                ListData.add(mreportHelper);
            }
        }
        final ReportArrayAdapter  adapter= (ReportArrayAdapter) new ReportArrayAdapter(
                currentTableName,this,
                R.layout.report_example,
                ListData );
        listView.setAdapter(adapter);
        cursor.close();

    }
}
