package com.example.finalexam_hizola;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class Login extends AppCompatActivity {
    public EditText Name, Pass;
    public static RadioButton user;
    public static RadioButton admin;
    public static Button addUser, viewData, prevbtn;
    myDbAdapter helper;
    myAdminDbAdapter admindb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (RadioButton) findViewById(R.id.super_userrg);
        admin = findViewById(R.id.super_adminrg);
        Name= (EditText) findViewById(R.id.editName_SA);
        Pass= (EditText) findViewById(R.id.editPass_SA);
        addUser = findViewById(R.id.button);
        viewData = findViewById(R.id.button2_SA);
        prevbtn = findViewById(R.id.sgn_btn);
        helper = new myDbAdapter(this);
        admindb = new myAdminDbAdapter(this);

        addUser.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v){
                if(user.isChecked())
                {
                    String user_name = Name.getText().toString();
                    String user_pass = Pass.getText().toString();
                    if(user_name.isEmpty() || user_pass.isEmpty())
                    {
                        Message.message(getApplicationContext(),"Enter Both Name and Password");
                    }else
                    {
                        myDbAdapter myDbadapter = new myDbAdapter(Login.this);
                        UserModelClass userModelClass = new UserModelClass(user_name, user_pass);
                        myDbadapter.adduser(userModelClass);
                        Message.message( getApplicationContext(),"User Created");
                    }
                }else if(admin.isChecked()){
                    String admin_name = Name.getText().toString();
                    String admin_pass = Pass.getText().toString();
                    if(admin_name.isEmpty() || admin_pass.isEmpty())
                    {
                        Message.message( getApplicationContext(),"Enter Both Name and Password");
                    }else
                    {
                        AdminModelClass adminModelClass = new AdminModelClass(admin_name, admin_pass);
                        admindb.add_admin(adminModelClass);
                        Message.message( getApplicationContext(),"Admin Created");
                    }
                }else{
                    Message.message( getApplicationContext(),"Wrong Credentials");
                }
            }

                });

            viewData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(user.isChecked())
                    {
                        startActivity(new Intent(Login.this, viewUsersActivity.class));
                        finish();
                    }else if(admin.isChecked())
                    {
                        startActivity(new Intent(Login.this, viewAdminActivity.class));
                        finish();
                    }

                }
            });

            prevbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
            });
            }
}


