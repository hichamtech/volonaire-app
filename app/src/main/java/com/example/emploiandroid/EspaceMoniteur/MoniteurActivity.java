package com.example.emploiandroid.EspaceMoniteur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.auth0.android.jwt.JWT;
import com.example.emploiandroid.Models.LoginModel;
import com.example.emploiandroid.Models.SharedPrefManager;
import com.example.emploiandroid.R;

public class MoniteurActivity extends AppCompatActivity {
    private static final String DEBUGTAG = MoniteurActivity.class.getCanonicalName();
    private TextView textView3;
    private CardView cardProfil,cardseance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(DEBUGTAG,"onCreate");
        setContentView(R.layout.activity_moniteur);
        LoginModel user =  SharedPrefManager.getInstance(this).getUser();

        JWT jwt = (JWT) getIntent().getParcelableExtra("jwt");
        textView3 = findViewById(R.id.textView3);
        textView3.setText(getString(R.string.Bonjour) + user.getEmail());

        cardProfil= findViewById(R.id.cardProfil);
        cardseance =  findViewById(R.id.cardseance);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(DEBUGTAG, "OnStart");

    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(DEBUGTAG, "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(DEBUGTAG, "onStop");

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(DEBUGTAG, "onRestart");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(DEBUGTAG, "onDestroy");

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(DEBUGTAG, "OnResume");

    }



    public void cardseance(View view) { openSeanceList(); }
    public void cardProfile(View view) {
        openprofile();
    }


    private void openSeanceList() {


    }
    private void openprofile() {
        Intent i = new Intent(MoniteurActivity.this,ProfileMonitorActivity.class);
        startActivity(i);

    }
}
