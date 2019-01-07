package com.example.android.attendencetakerfinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.attendencetakerfinal.studentContract.headerEntry;

public class createSubject extends AppCompatActivity {
    int default_data=1;

    public static String CURRENT_TABLENAME=null;

    DbHelper newDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);

        newDbHelper=new DbHelper(this);

        Button saveSubject = (Button) findViewById(R.id.save_new_subject);
        saveSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText subjectName=(EditText) findViewById(R.id.subject_name);
                String sName=subjectName.getText().toString().trim();

                EditText subjectCode=(EditText) findViewById(R.id.subject_code);
                String sCode=subjectCode.getText().toString().trim();

                EditText totalStudent=(EditText) findViewById(R.id.totalStudent);
                String tstudent=totalStudent.getText().toString().trim();
                Integer TStudent=Integer.parseInt(tstudent);

                //// ***** now create new table

                createNewSubjectTable(sCode,TStudent);

                Intent intent = new Intent(createSubject.this,defaultStudentList.class);
                startActivity(intent);
            }
        });

        Button excelbutton = (Button) findViewById(R.id.excelshit);
        excelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                default_data=0;
                EditText subjectName=(EditText) findViewById(R.id.subject_name);
                String sName=subjectName.getText().toString().trim();

                EditText subjectCode=(EditText) findViewById(R.id.subject_code);
                String sCode=subjectCode.getText().toString().trim();


                Integer TStudent=0;


                //// ***** now create new table

                createNewSubjectTable(sCode,TStudent);

                Intent intent = new Intent(createSubject.this,ExcelInput.class);
                intent.putExtra("TableName",CURRENT_TABLENAME);
                startActivity(intent);
            }
        });

    }

    ////////jhsgdfjsdfbjhb  CREATING DATABASE
    private void createNewSubjectTable(String subCode,Integer TotalStu)
    {
        SQLiteDatabase db=newDbHelper.getWritableDatabase();

        CURRENT_TABLENAME=subCode;

        String SQL_CREATE_STUDENT_TABLE =  "CREATE TABLE " + CURRENT_TABLENAME + " ("
                + headerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + headerEntry.headerNAME + " TEXT NOT NULL, "
                +headerEntry.headerReg + " INTEGER NOT NULL, "
                + headerEntry.headerPreviousday + " INTEGER NOT NULL, "
                + headerEntry.headerTotalClass + " INTEGER NOT NULL, "
                + headerEntry.headerPresentTotal + " INTEGER NOT NULL DEFAULT 0,"

                + headerEntry.termtest1 + " INTEGER NOT NULL DEFAULT 0,"

                + headerEntry.termtest2 + " INTEGER NOT NULL DEFAULT 0,"

                + headerEntry.termtest3 + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_STUDENT_TABLE);
        if(default_data==1)
        {
            for(Integer i=0;i<TotalStu;i++)
            {
                insertStudentInfo(CURRENT_TABLENAME,i);

            }
        }

        //Log.v(LOG_TAG,SQL_CREATE_STUDENT_TABLE);


    }
    // ******************INSERT DEFAULT INFORMATION***************

    private void insertStudentInfo(String TableName,int i){

        SQLiteDatabase db=newDbHelper.getWritableDatabase();

        ContentValues value=new ContentValues();
        value.put(headerEntry.headerNAME,"student name ");
        value.put(headerEntry.headerReg,i+1);
        value.put(headerEntry.headerTotalClass,0);
        value.put(headerEntry.headerPresentTotal,0);
        value.put(headerEntry.headerPreviousday,0);
        value.put(headerEntry.termtest1,0);
        value.put(headerEntry.termtest2,0);
        value.put(headerEntry.termtest1,0);
        db.insert(TableName,null,value);

    }


}
