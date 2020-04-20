package com.example.emploiandroid.EspaceAdministrator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emploiandroid.Models.Personne;
import com.example.emploiandroid.R;

public class AddClientFormActivity extends AppCompatActivity {

    private static final String DEBUGTAG = AdminActivity.class.getCanonicalName();

    private EditText txtNom,txtPrenom,txtCin,txtAdresse,txtTele,txtEmail,txtPassword,txtDateN;
    private Button btnAdd;
    private Personne client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client_form);
        txtNom = findViewById(R.id.txtName); //TODO:DELETE txtNom form string file
        txtPrenom = findViewById(R.id.txtPrenom);
        txtCin = findViewById(R.id.txtCin);
        txtAdresse = findViewById(R.id.txtAdresse);
        txtTele = findViewById(R.id.txtPhone); //TODO:DELETE txtNumform string file
        txtEmail = findViewById(R.id.txtMail);//TODO:DELETE txtEmail string file
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword = findViewById(R.id.txtDateN);
        btnAdd = findViewById(R.id.btnSave);
    }

    public void createClient(){

    }

    public void btnClick(View view) {
    }
}
