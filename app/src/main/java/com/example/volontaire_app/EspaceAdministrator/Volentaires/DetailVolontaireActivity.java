package com.example.volontaire_app.EspaceAdministrator.Volentaires;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volontaire_app.Models.Personne;
import com.example.volontaire_app.Models.URLs;
import com.example.volontaire_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DetailVolentaireActivity extends AppCompatActivity {

    private static final String DEBUGTAG = DetailVolentaireActivity.class.getCanonicalName();
    private ListView listView;
    private TextView txtId,txtNom,txtPrenom,txtCin,txtTel,txtAdresse,txtEmail;
    private Intent intent;
    private JSONArray dataArray;
    private Personne client;
    private JSONObject dataobj;
    private int idClient;
    private static String URL_BASE;
    private static String URL_BASE_SEANCE;
    private URLs urLs;

    //Start RCYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_client);

        urLs = new URLs();
        txtId = findViewById(R.id.txtid);
        txtNom = findViewById(R.id.txtNom);
        txtPrenom = findViewById(R.id.txtPrenom);
        txtCin = findViewById(R.id.txtCin);
        txtTel = findViewById(R.id.txtTel);
        txtAdresse = findViewById(R.id.txtAdresse);
        txtEmail = findViewById(R.id.txtEmail);
        listView = findViewById(R.id.lvSeance);
        intent = getIntent();
        // txtId.setText("this is my id" + );
        idClient = intent.getExtras().getInt("idClient");
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
        getDetailClient();
    }
    //END RECYCLE METHODS


    private void getDetailClient() {
        //  AfficherProgressDialog(this, "Chargement..","Recherch client",false);
        URL_BASE = urLs.URL_BASE_PERSONNE+idClient;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_BASE.trim(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        //dataModelArrayList = new ArrayList<>();

                        try {
                            //if(obj.optString("status").equals("true")){ //TODO:ADD STATUS OF RESPONSE IN API
                            JSONObject obj = new JSONObject(response);
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

                            client = new Personne();

                            // dataobj = dataArray.getJSONObject(i);
                            client.setId(obj.getInt("id"));
                            client.setNom(obj.getString("nom"));
                            client.setPrenom(obj.getString("prenom"));
                            client.setAdresse(obj.getString("adresse"));
                            client.setEmail(obj.getString("email"));
                            client.setNumTelephone(obj.getString("NumTelephone"));
                            //dataModelArrayList.add(client);


                            txtNom.setText(client.getNom());
                            txtPrenom.setText(client.getPrenom());
                            txtTel.setText(client.getNumTelephone());
                            txtAdresse.setText(client.getAdresse());
                            txtEmail.setText(client.getEmail());




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, //TODO: ADD API TOKEN
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}