package com.example.android.attendencetakerfinal;

import android.provider.BaseColumns;



/**
 * Created by emon on 2/3/18.
 */

public class studentContract {

    public static final class headerEntry implements BaseColumns {



        public final static String _ID=BaseColumns._ID;
        public final static String  headerNAME="NAME";
        public final static String  headerReg="Reg";
        public static final String headerPreviousday="Previousday";
        public static final String headerTotalClass="TotalClass";
        public static final String headerPresentTotal="PresentTotal";

        public static final String termtest1="termtest1";
        public static final String termtest2="termtest2";
        public static final String termtest3="termtest3";

    }
}
