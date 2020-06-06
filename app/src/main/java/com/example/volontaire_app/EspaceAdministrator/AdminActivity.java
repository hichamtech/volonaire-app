package com.example.emploiandroid.EspaceAdministrator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.auth0.android.jwt.JWT;
import com.example.emploiandroid.EspaceAdministrator.Volentaires.ListVolentaireActivity;
import com.example.emploiandroid.EspaceAdministrator.Demandeurs.ListDemandeurActivity;
import com.example.emploiandroid.Models.LoginModel;
import com.example.emploiandroid.Models.SharedPrefManager;
import com.example.emploiandroid.R;

public class AdminActivity extends AppCompatActivity {
    private static final String DEBUGTAG = AdminActivity.class.getCanonicalName();


    private TextView txtUserInfo;
    private CardView btnClients,btnMoniteurs,bntProfil,btnSeances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        Log.d(DEBUGTAG,"onCreate");
        setContentView(R.layout.activity_admin);
        LoginModel user =  SharedPrefManager.getInstance(this).getUser();

        JWT jwt = (JWT) getIntent().getParcelableExtra("jwt");


       //
       //Log.d(DEBUGTAG,"my id is" +  Integer.valueOf(user.getId()));



       //
       //Log.d(DEBUGTAG,"my id is" +  Integer.valueOf(user.getId()));



         //txtUserInfo.setText(user.getId());
        txtUserInfo = findViewById(R.id.txtUserInfo);
        txtUserInfo.setText(getString(R.string.Bonjour) + user.getEmail());

       /* Intent intent = getIntent();
        txtUserInfo.setText("Bienvenue " + intent.getExtras().getString("email") );*/

           // txtUserInfo.setText(user.getEmail());


        btnClients = findViewById(R.id.btnVolentaires);
        btnMoniteurs = findViewById(R.id.btnDemandeurs);
        bntProfil = findViewById(R.id.btnProfil);
        btnSeances =  findViewById(R.id.btnPosts);

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




    public void btnPosts(View view) { openSeanceList(); }
    public void btnVolentaires(View view) {
        openVolentaireList();
    }
    public void btnDemandeurs(View view) {
        openDemandeurList();
    }
    public void btnProfil(View view) {
        openProfil();
    }

    public void openSeanceList(){

      /*  Intent intent = new Intent(AdminActivity.this, AgendaCalandarView.class);
        startActivity(intent);*/
    }

    public void openVolentaireList(){
        Intent intent = new Intent(AdminActivity.this, ListVolentaireActivity.class);
       // intent.putExtra("token",loginModel.getToken());// TODO:Implement TOKEN
        startActivity(intent);
    }
    public void openDemandeurList(){
        Intent intent = new Intent(AdminActivity.this, ListDemandeurActivity.class);
        // intent.putExtra("token",loginModel.getToken());// TODO:Implement TOKEN
        startActivity(intent);
    }
    public void openProfil(){
        Intent intent = new Intent(AdminActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

}
