package com.example.finalexam_hizola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    Button login;

    myDbAdapter userDb;
    myAdminDbAdapter adminDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.loginguser);
        pass = findViewById(R.id.loginpass);
        login = findViewById(R.id.loginbtn1);
        userDb = new myDbAdapter(this);
        adminDb = new myAdminDbAdapter(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = user.getText().toString();
                String password = pass.getText().toString();
                if(name.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill up the form",Toast.LENGTH_SHORT);

                } else if(user.getText().toString().equals("super_admin") && pass.getText().toString().equals("superadmin12345"))
                {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(MainActivity.this, Login.class));

                }else {
                    Boolean user_result = userDb.checkUserCred(name,password);

                    if(user_result == true)
                    {
                        startActivity(new Intent(MainActivity.this, user.class));
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, " ", Toast.LENGTH_SHORT).show();
                    }

                    Boolean admin_result = adminDb.checkAdminCred(name,password);
                    if(admin_result == true)
                    {
                        startActivity(new Intent(MainActivity.this, admin.class));
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, " ", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}
