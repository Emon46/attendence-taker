package com.example.android.attendencetakerfinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class defaultStudentList extends AppCompatActivity {
    ListView listView;
    private DbHelper dbHelper;
    String StudentNameTemp[];
    String StudentRegTemp[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Student List");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_student_list);
        listView = (ListView) findViewById(R.id.listViewItem);
        dbHelper=new DbHelper(this);

        loadData();
//        Button SaveEditedList=(Button)findViewById(R.id.SaveEditedStudentList);
//        SaveEditedList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ///***********SAVE HERE UR DATA KORI NAI*****
//
//
//                Intent intent = new Intent(defaultStudentList.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });

    }
    public void loadData()
    {
        ArrayList<StudentHelper> ListData=new ArrayList<>();
        Cursor cursor=dbHelper.showStudentInformation(createSubject.CURRENT_TABLENAME);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                StudentHelper studentHelper=new StudentHelper(cursor.getString(0),
                        cursor.getString(1),cursor.getString(2));

                ListData.add(studentHelper);
            }
        }
        StudentArrayAdapter adapter= (StudentArrayAdapter) new StudentArrayAdapter(this,
                R.layout.list_student,
                ListData );
        listView.setAdapter(adapter);

        //ITEM CLICk KAJ SESH HOI NAI!!!!

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditText studentName=(EditText)findViewById(R.id.NameView);
                StudentNameTemp[position]=studentName.getText().toString().trim();
                EditText studentReg=(EditText)findViewById(R.id.RegView);
                StudentRegTemp[position]=studentReg.getText().toString().trim();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                Intent intent = new Intent(defaultStudentList.this,MainActivity.class);
                startActivity(intent);

        }
        return true;
    }
}
