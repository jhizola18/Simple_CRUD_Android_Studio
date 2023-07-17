package com.example.finalexam_hizola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class admin extends AppCompatActivity {
    EditText Name, Pass;
    Button addUser, viewData, signout;
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        addUser = (Button) findViewById(R.id.button);
        viewData = (Button) findViewById(R.id.button2);
        signout = (Button) findViewById(R.id.sgnbtn);
        helper = new myDbAdapter(this);


        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = Name.getText().toString();
                String user_pass = Pass.getText().toString();
                if(user_name.isEmpty() || user_pass.isEmpty())
                {
                    Message.message(getApplicationContext(),"Enter Both Name and Password");
                }else
                {
                    myDbAdapter myDbadapter = new myDbAdapter(admin.this);
                    UserModelClass userModelClass = new UserModelClass(user_name, user_pass);
                    myDbadapter.adduser(userModelClass);
                    Message.message( getApplicationContext(),"User Created");
                }
            }


        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin.this, viewUsersMadminActivity.class));
                finish();
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin.this, MainActivity.class));
                finish();
            }
        });
    }




}



