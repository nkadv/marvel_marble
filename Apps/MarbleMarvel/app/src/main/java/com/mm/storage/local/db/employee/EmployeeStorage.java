package com.mm.storage.local.db.employee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mm.employees.Employee;
import com.mm.labours.Labour;
import com.mm.storage.local.db.DBHelper;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.DBStorage;

/**
 * Created by nayerram on 15-10-2016.
 */
public class EmployeeStorage implements IEmployeeStorage {
    @Override
    public boolean persistEmployee(Context context, Employee employee) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DBSchema.Employees.EMPNAME,employee.name);
        values.put(DBSchema.Employees.USERNAME,employee.userName);
        values.put(DBSchema.Employees.PWD,employee.pwd);
        values.put(DBSchema.Employees.EMPTYPE,employee.empType);

        //  TODO:Return Value check
        db.insert(DBSchema.Employees.TABLE_NAME,null,values);

        db.close();
        return true;
    }

    @Override
    public Cursor getAllEmployees(Context context) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query(
                DBSchema.Employees.TABLE_NAME,              // The table to query
                null,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        return c;

    }

    @Override
    public Labour getEmployee(Context context, Integer empId) {
        return null;
    }

    @Override
    public Labour getEmployee(Context context, String empName) {
        return null;
    }

    @Override
    public Cursor getEmployees(Context context, String searchString) {
        DBHelper dbHelper = DBStorage.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


//        select * from <tablle_name> where <Column> like %searchString%'
        Cursor c = db.query(
                true,
                DBSchema.Employees.TABLE_NAME,              // The table to query
                null,                               // The columns to return
                DBSchema.Labours.NAME + " LIKE ?",  // The columns for the WHERE clause
                new String[] {"%"+searchString+"%"},                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null,
                null// The sort order
        );

        return c;
    }
}
