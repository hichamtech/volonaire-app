package com.example.emploiandroid.EspaceClient;

import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.emploiandroid.LoginActivity;
import com.example.emploiandroid.Models.LoginModel;
import com.example.emploiandroid.Models.SharedPrefManager;
import com.example.emploiandroid.R;

public class ClientActivity extends AppCompatActivity {
    private static final String DEBUGTAG = ClientActivity.class.getCanonicalName();
    private CardView cardProfil,cardseance;

    TextView txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(DEBUGTAG,"onCreate");

        setContentView(R.layout.activity_client);
      /*  if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }*/
        LoginModel user =  SharedPrefManager.getInstance(this).getUser();
        txtEmail = findViewById(R.id.textView2);
        txtEmail.setText( " Espace Client Bonjour" + user.getEmail());
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
        Intent i = new Intent(ClientActivity.this,ProfileClientActivity.class);
        startActivity(i);
    }
}
