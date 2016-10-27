package com.mm.storage.local.db;

import android.provider.BaseColumns;

/**
 * Created by nayerram on 03-10-2016.
 */
public class DBSchema {


    private DBSchema(){
    }

    /* Labours Table */
    public static final String SQL_CREATE_LABOURS =
            "CREATE TABLE IF NOT EXISTS " + Labours.TABLE_NAME + " (" +
                    Labours._ID + " INTEGER PRIMARY KEY," +
                    Labours.LABOUR_ID + " INTEGER," +
                    Labours.NAME + " TEXT," +
                    Labours.FATHER_NAME + " TEXT," +
                    Labours.PLACE + " TEXT," +
                    Labours.DOJ + " TEXT," +
                    Labours.RATE + " REAL," +
                    Labours.PHOTO + " TEXT," +
                    Labours.BALANCE + " REAL" + " )";



    public static class Labours implements BaseColumns {
        public static final String TABLE_NAME = "Labours";
        public static final String LABOUR_ID = "labour_id";
        public static final String NAME = "name";
        public static final String FATHER_NAME = "father_name";
        public static final String PLACE = "place";
        public static final String DOJ = "doj";
        public static final String RATE = "rate";
        public static final String PHOTO = "photo";
        public static final String BALANCE = "balance";
    }

    /*Materials Table*/

    public static final String SQL_CREATE_MATERIALS =
            "CREATE TABLE IF NOT EXISTS " + Materials.TABLE_NAME + " (" +
                    Materials._ID + " INTEGER PRIMARY KEY," +
                    Materials.NAME + " TEXT," +
                    Materials.CATEGORY + " TEXT," +
                    Materials.UNITS + " TEXT," +
                    Materials.COSTPERUNIT + " REAL" +
                    " )";


    public static class Materials implements BaseColumns {
        public static final String TABLE_NAME = "materials";
        public static final String NAME = "name";
        public static final String CATEGORY = "category";
        public static final String UNITS = "units";
        public static final String COSTPERUNIT = "costPerUnit";
    }

    /*Sites Table*/
    public static final String SQL_CREATE_SITES =
            "CREATE TABLE IF NOT EXISTS " + Sites.TABLE_NAME + " (" +
                    Sites._ID + " INTEGER PRIMARY KEY," +
                    Sites.NAME + " TEXT," +
                    Sites.LOCATION + " TEXT," +
                    Sites.STARTDATE + " TEXT," +
                    Sites.STATUS + " TEXT" +
                    " )";


    public static class Sites implements BaseColumns {
        public static final String TABLE_NAME = "sites";
        public static final String NAME = "name";
        public static final String LOCATION = "location";
        public static final String STARTDATE = "start_date";
        public static final String STATUS = "status";
    }

    /*Labour Allocation*/
    public static final String SQL_CREATE_SITE_LABOURS =
            "CREATE TABLE IF NOT EXISTS " + SiteLabours.TABLE_NAME + " (" +
                    SiteLabours._ID + " INTEGER PRIMARY KEY," +
                    SiteLabours.LABOUR_ID + " INTEGER," +
                    SiteLabours.SITE_ID + " INTEGER," +
                    SiteLabours.START_DATE + " TEXT," +
                    SiteLabours.END_DATE + " TEXT," +
                    SiteLabours.STATUS + " INTEGER" +
                    " )";


    public static class SiteLabours implements BaseColumns {
        public static final String TABLE_NAME = "site_labours";
        public static final String LABOUR_ID = "labour_id";
        public static final String SITE_ID = "site_id";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
        public static final String STATUS ="status";
    }

    /*Material Allocation*/
    public static final String SQL_CREATE_SITE_MATERIALS =
            "CREATE TABLE IF NOT EXISTS " + SiteMaterials.TABLE_NAME + " (" +
                    SiteMaterials._ID + " INTEGER PRIMARY KEY," +
                    SiteMaterials.MATERIAL_ID + " INTEGER," +
                    SiteMaterials.SITE_ID + " INTEGER," +
                    SiteMaterials.DATE + " TEXT," +
                    SiteMaterials.QUANTITY + " REAL," +
                    SiteMaterials.AMOUNT + " REAL," +
                    SiteMaterials.EMP_ID + " INTEGER" +
                    " )";

    public static class SiteMaterials implements BaseColumns {
        public static final String TABLE_NAME = "site_materials";
        public static final String MATERIAL_ID = "material_id";
        public static final String SITE_ID = "site_id";
        public static final String DATE = "date";
        public static final String QUANTITY = "quantity";
        public static final String AMOUNT = "amount";
        public static final String EMP_ID = "emp_id";
    }

    public static class status{
        public static final Integer active =1;
        public static final Integer inactive =0;
    }

    /*Employee Table*/
    public static final String SQL_CREATE_EMPLOYEES =
            "CREATE TABLE IF NOT EXISTS " + Employees.TABLE_NAME + " (" +
                    Employees._ID + " INTEGER PRIMARY KEY," +
                    Employees.EMPNAME + " TEXT," +
                    Employees.USERNAME + " TEXT," +
                    Employees.PWD + " TEXT," +
                    Employees.EMPTYPE + " INTEGER" +
                    " )";


    public static class Employees implements BaseColumns {
        public static final String TABLE_NAME = "employees";
        public static final String EMPNAME = "name";
        public static final String USERNAME = "user_name";
        public static final String PWD = "pwd";
        public static final String EMPTYPE = "emp_type";
    }

}
/* CREATE TABLE Persons
        (
                PersonID int,
                LastName varchar(255),
FirstName varchar(255),
Address varchar(255),
City varchar(255)
);*/
