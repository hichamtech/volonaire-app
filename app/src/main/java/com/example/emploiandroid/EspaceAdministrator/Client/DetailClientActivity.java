package com.example.emploiandroid.EspaceAdministrator.Client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.emploiandroid.R;

public class DetailClientActivity extends AppCompatActivity {

    TextView txtId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_client);

        txtId = findViewById(R.id.txtid);
        Intent intent = getIntent();
        txtId.setText("this is my id" + intent.getExtras().getInt("idClient") );
    }
}
