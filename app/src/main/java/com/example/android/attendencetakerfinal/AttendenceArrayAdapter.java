package com.example.android.attendencetakerfinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by emon on 2/22/18.
 */

public class AttendenceArrayAdapter extends ArrayAdapter<attendenceHelper> implements ListAdapter {
    Context mcontext;
    int mresource;
    String CURRENT_TABLE_NAME;
    TextToSpeech t1;
    DbHelper newDbHelper;
    boolean[] presentArray; // eta sobgulo present checkbox er value rakhbe
    boolean[] absentArray;


    CheckBox absentcb;
    CheckBox presentcb;

    public AttendenceArrayAdapter(String currentTableName, @NonNull Context context, int resource, @NonNull ArrayList<attendenceHelper> objects) {
        super(context, resource, objects);
        presentArray = new boolean[objects.size()];
        absentArray = new boolean[objects.size()];
        mcontext = context;
        mresource = resource;
        CURRENT_TABLE_NAME = currentTableName;

        for (int i = 0; i < objects.size(); i++) {

            /* initially sobgula CheckBox unchecked orthat false */
            presentArray[i] = false;
            absentArray[i] = false;
        }
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        newDbHelper = new DbHelper(mcontext);
        SQLiteDatabase db = newDbHelper.getWritableDatabase();

        final Cursor cursor = newDbHelper.showStudentInformation(CURRENT_TABLE_NAME);

        String name = getItem(position).NAME;
        final String Reg = getItem(position).Reg;
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource, parent, false);

        TextView RegView = (TextView) convertView.findViewById(R.id.attendenceReg);
        TextView Nameview = (TextView) convertView.findViewById(R.id.attendenceName);

        presentcb = (CheckBox) convertView.findViewById(R.id.presentbox);
        absentcb = (CheckBox) convertView.findViewById(R.id.absent);


        RegView.setText(Reg);
        Nameview.setText(name);

        /*boolean value dekhe dekhe checked na unchecked seta bosiye dilam
         * initially presentArray/absentArray te sob value false chilo so sob checkBox unchecked,
         * porerbar presentAll() method call howar por abar getView()
          * method run hobe,then getView() er ei line a code asar por
          * presentArray te sob value true thakbe and absentArray te
           * sobgula false,so checkBox sei onujayi checked hobe
            * ekhane kono loop chalai ni karon listView er prottekta item
            * er jonno ei getView() ekbar run hobe so just item
            * er position er sathe mil rekhe array theke true/false
            * value nilei hobe*/
        presentcb.setChecked(presentArray[position]);
        absentcb.setChecked(absentArray[position]);

        presentcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    t1 = new TextToSpeech(mcontext, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                t1.setLanguage(Locale.UK);
                                int reg = Integer.parseInt(Reg);

                                //EXCEPTIOn  THIK KOORO
                                cursor.moveToPosition(position);
                                if (cursor.moveToNext()) {
                                    String text = Integer.toString(reg);
                                    text = cursor.getString(2);
                                    t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                                }
                            }
                        }
                    });

                    if (AttendenceOfStudent.ispresent.get(position) == 0) {
                        presentArray[position]=true;
                        absentArray[position]=false;
                        notifyDataSetChanged();
                        AttendenceOfStudent.ispresent.set(position, 1);
                    }
                    else {
                        AttendenceOfStudent.ispresent.set(position, 0);
                    }
                }
            }
        });
        absentcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    t1 = new TextToSpeech(mcontext, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                t1.setLanguage(Locale.UK);
                                int reg = Integer.parseInt(Reg);

                                cursor.moveToPosition(position);
                                if (cursor.moveToNext()) {
                                    String text = Integer.toString(reg);
                                    text = cursor.getString(2);
                                    t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                                }
                            }
                        }
                    });
                    if (AttendenceOfStudent.ispresent.get(position) == 1) {
                        presentArray[position]=false;
                        absentArray[position]=true;
                        notifyDataSetChanged();

                        AttendenceOfStudent.ispresent.set(position, 0);
                    }
                    else {
                        AttendenceOfStudent.ispresent.set(position, 0);
                    }
                }
            }
        });

        return convertView;
    }

    public void presentAll() {
        for (int i = 0; i < getCount(); i++) {
            presentArray[i] = true;
            absentArray[i] = false;

            AttendenceOfStudent.ispresent.set(i, 1);
        }
        notifyDataSetChanged();  //ei  tar karone prottekta listView item er jonno getView() method abar call hobe
    }

    public void absentAll(){
        for (int i = 0; i< getCount();i++){
            presentArray[i] = false;
            absentArray[i] = true;

            AttendenceOfStudent.ispresent.set(i, 0);
        }
        notifyDataSetChanged();
    }

}
