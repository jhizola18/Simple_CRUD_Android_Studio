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

public class viewAdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    Button exit_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admin);

        exit_button = findViewById(R.id.exit_button2);
        recyclerView = findViewById(R.id.adminView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        myAdminDbAdapter myAdminDbAdapter = new myAdminDbAdapter(this);
        List<AdminModelClass> adminModelClasses = myAdminDbAdapter.getAdminList();

        if(adminModelClasses.size() > 0)
        {
            AdminAdapterClass adminAdapterClass = new AdminAdapterClass(adminModelClasses, viewAdminActivity.this);
            recyclerView.setAdapter(adminAdapterClass);
        }else{
            Toast.makeText(this,"No Users in the database",Toast.LENGTH_SHORT).show();
        }

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewAdminActivity.this, Login.class));
            }
        });
    }
}