package com.example.finalexam_hizola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class user extends AppCompatActivity {

    Button signOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        signOut = findViewById(R.id.signoutbtn);

        myDbAdapter myDbAdapterUser = new myDbAdapter(this);
        List<UserModelClass> userModelClasses = myDbAdapterUser.getUserList();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user.this, MainActivity.class));
            }
        });
    }
}