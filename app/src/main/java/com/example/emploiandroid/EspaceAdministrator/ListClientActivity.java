package com.example.emploiandroid.EspaceAdministrator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.emploiandroid.LoginActivity;
import com.example.emploiandroid.Models.Personne;
import com.example.emploiandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListClientActivity extends AppCompatActivity {
    private static final String DEBUGTAG = ListClientActivity.class.getCanonicalName();
    private static String URL_BASE = "http://192.168.1.11:8000/api/personnes?roles=ROLE_CLIENT";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<Personne> dataModelArrayList;
    private ListClientAdapter listAdapter;
    Personne client ;
    Button btnAddClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);

        listView = findViewById(R.id.lv);
        btnAddClient = findViewById(R.id.btnAddClient);
        getListClients();
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

    protected void onResume() {
        super.onResume();
        Log.d(DEBUGTAG, "OnResume");
    }

    private void getListClients(){
        AfficherProgressDialog(this, "Chargement..","Recherch client",false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_BASE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        dataModelArrayList = new ArrayList<>();

                            try {
                                //if(obj.optString("status").equals("true")){ //TODO:ADD STATUS OF RESPONSE IN API
                                JSONObject obj = new JSONObject(response);
                                JSONArray dataArray  = obj.getJSONArray("hydra:member");
                                for (int i = 0; i < dataArray.length(); i++)
                                {
                                client = new Personne();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                client.setNom(dataobj.getString("nom"));
                                client.setEmail(dataobj.getString("email"));
                                client.setNumTelephone(dataobj.getString("NumTelephone"));
                                dataModelArrayList.add(client);
                             }
                                setupListview();
                                // }
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



    //FUNCTION TO SETUP THE LIST VIEW
    private void setupListview(){
        supprimerSimpleProgressDialog();
        listAdapter = new ListClientAdapter(this, dataModelArrayList);
        listView.setAdapter(listAdapter);
    }
    //FUNCTION TO DELETE PROGRESS DIALOG
    public static void supprimerSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //FUNCTION TO DISPLAY PROGRESS DIAALOG
    public static void AfficherProgressDialog(Context context, String title,
                                              String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void OpenAddForm(){

        Intent intent = new Intent(ListClientActivity.this, AddClientFormActivity.class);
        //TODO:SEND TOKEN
        startActivity(intent);

    }
    public void btnAddClient(View view) {

        if (view ==btnAddClient){
            OpenAddForm();
        }

    }
}
