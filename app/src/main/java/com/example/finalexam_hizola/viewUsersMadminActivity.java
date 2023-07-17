package com.example.finalexam_hizola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class viewUsersMadminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button exit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users_madmin);

        exit_btn = findViewById(R.id.exit_button1);
        recyclerView = findViewById(R.id.userView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        myDbAdapter myDbAdapterUser = new myDbAdapter(this);
        List<UserModelClass> userModelClasses = myDbAdapterUser.getUserList();

        if(userModelClasses.size() > 0)
        {
            UserAdapterClass userAdapterClass = new UserAdapterClass(userModelClasses, viewUsersMadminActivity.this);
            recyclerView.setAdapter(userAdapterClass);
        }else{
            Toast.makeText(this,"No Users in the database",Toast.LENGTH_SHORT).show();
        }

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewUsersMadminActivity.this, admin.class));
                finish();
            }
        });
    }
}