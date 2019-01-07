package com.example.android.attendencetakerfinal;

/**
 * Created by emon on 2/22/18.
 */

public class reportHelper {
    String  reportReg;
    String reportpreviousday;
    String reporttotal;
    String reportpresent;

    public reportHelper(String Reg,String  previous,String reporttotal,String reportpresent)
    {
        this.reportReg=Reg;
        this.reportpreviousday=previous;
        this.reporttotal=reporttotal;
        this.reportpresent=reportpresent;
    }

}
