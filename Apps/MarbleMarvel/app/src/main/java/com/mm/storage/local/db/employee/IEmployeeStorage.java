package com.mm.storage.local.db.employee;

import android.content.Context;
import android.database.Cursor;

import com.mm.employees.Employee;
import com.mm.labours.Labour;

/**
 * Created by nayerram on 15-10-2016.
 */
public interface IEmployeeStorage {
    public boolean persistEmployee(Context context, Employee employee);
    public Cursor getAllEmployees(Context context);
    public Labour getEmployee(Context context,Integer empId);
    public Labour getEmployee(Context context,String empName);
    public Cursor getEmployees(Context context, String searchString);
}
