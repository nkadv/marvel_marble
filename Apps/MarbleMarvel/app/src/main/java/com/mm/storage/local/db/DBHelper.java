package com.mm.storage.local.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mm.employees.Employee;

/**
 * Created by nayerram on 03-10-2016.
 */
public class DBHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mm.db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBSchema.SQL_CREATE_LABOURS);
        sqLiteDatabase.execSQL(DBSchema.SQL_CREATE_MATERIALS);
        sqLiteDatabase.execSQL(DBSchema.SQL_CREATE_SITES);
        sqLiteDatabase.execSQL(DBSchema.SQL_CREATE_SITE_LABOURS);
        sqLiteDatabase.execSQL(DBSchema.SQL_CREATE_EMPLOYEES);
        sqLiteDatabase.execSQL(DBSchema.SQL_CREATE_SITE_MATERIALS);


        populateSampleData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void populateSampleData(SQLiteDatabase db)
    {
        //Labours
        ContentValues values = new ContentValues();

        values.put(DBSchema.Labours.LABOUR_ID,210);
        values.put(DBSchema.Labours.NAME,"Narayan lal prajapat");
        values.put(DBSchema.Labours.FATHER_NAME,"");
        values.put(DBSchema.Labours.PLACE,"Kurawar");
        values.put(DBSchema.Labours.DOJ,"15/10/2016");
        values.put(DBSchema.Labours.RATE,300);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        values.put(DBSchema.Labours.LABOUR_ID,562);
        values.put(DBSchema.Labours.NAME,"Premchand Ravat");
        values.put(DBSchema.Labours.FATHER_NAME,"Meghaji");
        values.put(DBSchema.Labours.PLACE,"Kharkatalab");
        values.put(DBSchema.Labours.DOJ,"15/10/2016");
        values.put(DBSchema.Labours.RATE,300);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        values.put(DBSchema.Labours.LABOUR_ID,79);
        values.put(DBSchema.Labours.NAME,"Shantilal Ravat");
        values.put(DBSchema.Labours.FATHER_NAME,"Sokaji");
        values.put(DBSchema.Labours.PLACE,"Payatalab");
        values.put(DBSchema.Labours.DOJ,"15/10/2016");
        values.put(DBSchema.Labours.RATE,250);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        values.put(DBSchema.Labours.LABOUR_ID,478);
        values.put(DBSchema.Labours.NAME,"Raju Ravat");
        values.put(DBSchema.Labours.FATHER_NAME,"DungarJi");
        values.put(DBSchema.Labours.PLACE,"Jethana");
        values.put(DBSchema.Labours.DOJ,"15/10/2016");
        values.put(DBSchema.Labours.RATE,300);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        values.put(DBSchema.Labours.LABOUR_ID,400);
        values.put(DBSchema.Labours.NAME,"Lalu ram Ravat");
        values.put(DBSchema.Labours.FATHER_NAME,"DevaJi");
        values.put(DBSchema.Labours.PLACE,"Bhagal");
        values.put(DBSchema.Labours.DOJ,"12/10/2016");
        values.put(DBSchema.Labours.RATE,200);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        values.put(DBSchema.Labours.LABOUR_ID,350);
        values.put(DBSchema.Labours.NAME,"Suraj Paswan");
        values.put(DBSchema.Labours.FATHER_NAME,"NanakJi");
        values.put(DBSchema.Labours.PLACE,"Bihar");
        values.put(DBSchema.Labours.DOJ,"15/10/2016");
        values.put(DBSchema.Labours.RATE,300);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        values.put(DBSchema.Labours.LABOUR_ID,218);
        values.put(DBSchema.Labours.NAME,"Shankarlal Ravat");
        values.put(DBSchema.Labours.FATHER_NAME,"HiraJi");
        values.put(DBSchema.Labours.PLACE,"Dhaila");
        values.put(DBSchema.Labours.DOJ,"15/10/2016");
        values.put(DBSchema.Labours.RATE,300);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        values.put(DBSchema.Labours.LABOUR_ID,912);
        values.put(DBSchema.Labours.NAME,"Rajendra Singh");
        values.put(DBSchema.Labours.FATHER_NAME,"Keshar Singh");
        values.put(DBSchema.Labours.PLACE,"Jambuda");
        values.put(DBSchema.Labours.DOJ,"15/10/2016");
        values.put(DBSchema.Labours.RATE,300);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        values.put(DBSchema.Labours.LABOUR_ID,475);
        values.put(DBSchema.Labours.NAME,"Meghraj Ravat");
        values.put(DBSchema.Labours.FATHER_NAME,"TejaJi");
        values.put(DBSchema.Labours.PLACE,"Salothra");
        values.put(DBSchema.Labours.DOJ,"15/10/2016");
        values.put(DBSchema.Labours.RATE,300);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);

        values.put(DBSchema.Labours.LABOUR_ID,88);
        values.put(DBSchema.Labours.NAME,"Ramesh Ravat");
        values.put(DBSchema.Labours.FATHER_NAME,"BheraJi");
        values.put(DBSchema.Labours.PLACE,"Salothra");
        values.put(DBSchema.Labours.DOJ,"15/10/2016");
        values.put(DBSchema.Labours.RATE,300);
        values.put(DBSchema.Labours.PHOTO,"");
        values.put(DBSchema.Labours.BALANCE,0.0);
        db.insert(DBSchema.Labours.TABLE_NAME,null,values);


        //Materials

        ContentValues mValues = new ContentValues();
        mValues.put(DBSchema.Materials.NAME,"Marble Blade");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Granite Cutting Blade");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"CD Blade");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"3 inch Cup Blade");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Carbon (Cutting)");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Carbon (Polish)");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Carbon (Grinder)");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Bits 6mm");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Bits 8mm");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Bits 10mm");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Bits 20mm");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Bits 24mm");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Bits 30mm");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Bits 36mm");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Bits 42mm");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Bits 48mm");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Drill Chuck");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones FF1m");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones FF1m");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones FF2m");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones FF3");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones FF4");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones FF5");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones FF5x");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Small m/c stones CRB1m");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones CRB2m");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones CRB3");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones CRB4");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones CRB5");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Polishing Big m/c stones CRB5x");
        mValues.put(DBSchema.Materials.UNITS,"Number");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Epoxy Resin");
        mValues.put(DBSchema.Materials.UNITS,"Kg");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        mValues.put(DBSchema.Materials.NAME,"Epoxy Hardner");
        mValues.put(DBSchema.Materials.UNITS,"Kg");
        mValues.put(DBSchema.Materials.COSTPERUNIT,250.0);
        db.insert(DBSchema.Materials.TABLE_NAME,null,mValues);

        //Sites
        ContentValues sValues = new ContentValues();
        sValues.put(DBSchema.Sites.NAME,"Platinium");
        sValues.put(DBSchema.Sites.LOCATION,"Basawangudi, Bangalore");
        sValues.put(DBSchema.Sites.STARTDATE,"10/10/2016");
        db.insert(DBSchema.Sites.TABLE_NAME,null,sValues);

        sValues.put(DBSchema.Sites.NAME,"Mukesh Ji");
        sValues.put(DBSchema.Sites.LOCATION,"Jayanagar, Bangalore");
        sValues.put(DBSchema.Sites.STARTDATE,"15/10/2016");
        db.insert(DBSchema.Sites.TABLE_NAME,null,sValues);

        //Employees
        ContentValues eValues = new ContentValues();

        eValues.put(DBSchema.Employees.EMPNAME,"Vijay");
        eValues.put(DBSchema.Employees.USERNAME,"vijay");
        eValues.put(DBSchema.Employees.PWD,"1234");
        eValues.put(DBSchema.Employees.EMPTYPE, Employee.superEmp);
        db.insert(DBSchema.Employees.TABLE_NAME,null,eValues);

        eValues.put(DBSchema.Employees.EMPNAME,"Shyam");
        eValues.put(DBSchema.Employees.USERNAME,"shyam");
        eValues.put(DBSchema.Employees.PWD,"1234");
        eValues.put(DBSchema.Employees.EMPTYPE, Employee.normalEmp);
        db.insert(DBSchema.Employees.TABLE_NAME,null,eValues);

    }
}
