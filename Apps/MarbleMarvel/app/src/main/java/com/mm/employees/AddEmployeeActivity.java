package com.mm.employees;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mm.entry.R;
import com.mm.storage.local.db.employee.EmployeeStorage;

public class AddEmployeeActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner emp_type;
    EditText emp_name,login_user_name,login_pwd;
    TextInputLayout til_name, til_user_name,til_pwd;

    Button bt_add;
    EmployeeStorage eStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        emp_name = (EditText)findViewById(R.id.ae_emp_name);
        login_user_name = (EditText)findViewById(R.id.ae_login_user_name);
        login_pwd = (EditText)findViewById(R.id.ae_login_pwd);

        til_name = (TextInputLayout)findViewById(R.id.ae_input_layout_name);
        til_user_name = (TextInputLayout)findViewById(R.id.ae_input_layout_user_id);
        til_pwd = (TextInputLayout)findViewById(R.id.ae_input_layout_pwd);

        /*Populate Employee Type, Spinner with an Array Resource*/
        emp_type = (Spinner)findViewById(R.id.ae_emp_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.emp_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emp_type.setAdapter(adapter);

        Button bt_add = (Button)findViewById(R.id.ae_add);
        bt_add.setOnClickListener(this);
        eStorage = new EmployeeStorage();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ae_add:
                if(validateForm()) {
                    saveEmployee();
                    startActivity(new Intent(getApplicationContext(),com.mm.employees.ViewEmployeesActivity.class));
                }else{
                    //  TODO: Toast Notification ??
                }
                break;
            default:
                break;
        }
    }

    private boolean validateForm(){

        Boolean bRet=true;
        if(emp_name.getText().toString().trim().length() == 0) {
            til_name.setError("Enter Valid Name");
            bRet = false;
        }
        else
            til_name.setErrorEnabled(false);

        if(login_user_name.getText().toString().trim().length() == 0) {
            til_user_name.setError("Enter Valid Login User Name");
            bRet = false;
        }
        else
            til_user_name.setErrorEnabled(false);

        if(login_pwd.getText().toString().trim().length() == 0) {
            til_pwd.setError("Enter Valid login password");
            bRet = false;
        }
        else
            til_pwd.setErrorEnabled(false);

        return bRet;
    }

    private boolean saveEmployee(){

        Employee emp = new Employee();

        emp.name = emp_name.getText().toString();
        emp.userName = login_user_name.getText().toString();
        emp.pwd = login_pwd.getText().toString();

        String type = emp_type.getSelectedItem().toString();
        if(type.contains("Super")){
            emp.empType = Employee.superEmp;
        }else if(type.contains("Normal")){
            emp.empType = Employee.superEmp;
        }else{
            Toast.makeText(this,"Invalid Emp Type..",Toast.LENGTH_LONG).show();
        }

        //  TODO: Code to handle failure case
        eStorage.persistEmployee(this,emp);
        return true;
    }
}
