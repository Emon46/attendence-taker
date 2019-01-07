package com.example.android.attendencetakerfinal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by emon on 2/22/18.
 */

public class ReportArrayAdapter extends ArrayAdapter<reportHelper> implements ListAdapter {
        Context mcontext;
        int mresource;
        String CURRENT_TABLE_NAME;


        public ReportArrayAdapter(String currentTableName, @NonNull Context context, int resource, @NonNull ArrayList<reportHelper> objects) {
                super(context, resource, objects);
                mcontext=context;
                mresource=resource;
                CURRENT_TABLE_NAME=currentTableName;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                String reg=getItem(position).reportReg;
                String previous=getItem(position).reportpreviousday;
                String total=getItem(position).reporttotal;
                String present=getItem(position).reportpresent;

                LayoutInflater inflater=LayoutInflater.from(mcontext);
                convertView=inflater.inflate(mresource,parent,false);

                TextView RegView=(TextView) convertView.findViewById(R.id.regreport);
                TextView previousView=(TextView) convertView.findViewById(R.id.previousreport);
                TextView totalView=(TextView) convertView.findViewById(R.id.totalreport);
                TextView presentView=(TextView) convertView.findViewById(R.id.presentreport);

                RegView.setText(reg);
                previousView.setText(previous);
                totalView.setText(total);
                presentView.setText(present);

                return convertView;
        }
}
