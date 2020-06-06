package com.example.volontaire_app.EspaceAdministrator.reclamations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volontaire_app.EspaceAdministrator.Demandes.EditDemandeActivity;
import com.example.volontaire_app.EspaceAdministrator.Demandes.ListeDemdandesActivity;
import com.example.volontaire_app.EspaceAdministrator.Demandeurs.DetailDemandeurActivity;
import com.example.volontaire_app.EspaceAdministrator.ListReclamationAdapter;
import com.example.volontaire_app.EspaceAdministrator.ListePostAdapter;
import com.example.volontaire_app.Models.Personne;
import com.example.volontaire_app.Models.Post;
import com.example.volontaire_app.Models.Reclamation;
import com.example.volontaire_app.Models.URLs;
import com.example.volontaire_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListReclmationActivity extends AppCompatActivity {

    private static final String DEBUGTAG = ListeDemdandesActivity.class.getCanonicalName();

    private static ProgressDialog mProgressDialog;
    private ListView listView;
    private ArrayList<Reclamation> dataModelArrayList;
    private ListReclamationAdapter listReclamationAdapter;
    private Reclamation reclamation;
    private Button btnAddDemande;

    int lockClikedItemIndex;
    private JSONArray dataArray;
    private static  final  int EDIT = 0,DElETE=1, DETAIL =2;
    private JSONObject dataobj;

    private URLs urLs;
    //TO SEND DATA to the OTHERS ACTIVITY SEE FUNCTION ItemLongClick
    private  int idReclamation;
    private  String objet, contenu;
    private Personne author;
    //Start RCYCLE METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reclmation);
        urLs = new URLs();
        listView = findViewById(R.id.lv_admin_reclamations);
        btnAddDemande = findViewById(R.id.btnAddDemandeur);
        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                idReclamation = dataModelArrayList.get(position).getIdReclamation();
                objet = dataModelArrayList.get(position).getObjet();
                contenu = dataModelArrayList.get(position).getContenu();
                lockClikedItemIndex = position;
                return false;
            }
        });


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
        getListReclamations();

    }
    //END RECYCLE METHODS


    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        // menu.setHeaderIcon(R.drawable.editicon);
        menu.setHeaderTitle("Options");
        menu.add(Menu.NONE, EDIT, menu.NONE,"Modifier");
        menu.add(Menu.NONE, DElETE, menu.NONE,"Supprimer");
        menu.add(Menu.NONE, DETAIL,menu.NONE,"Detail");
    }

    //CRUD Reclamation


    private void getListReclamations(){
        AfficherProgressDialog(this, "Chargement..","Recherche Reclamations",false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urLs.URL_BASE_Reclamation,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        dataModelArrayList = new ArrayList<>();

                        try {
                            //if(obj.optString("status").equals("true")){ //TODO:ADD STATUS OF RESPONSE IN API
                            JSONObject obj = new JSONObject(response);
                            dataArray  = obj.getJSONArray("hydra:member");
                            JSONArray dataArrayVille ;
                            JSONObject DataVilleObj;

                            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                            String urlVille;
                            for (int i = 0; i < dataArray.length(); i++)
                            {
                                reclamation = new Reclamation();

                                dataobj = dataArray.getJSONObject(i);
                                reclamation.setIdReclamation(dataobj.getInt("id"));
                                reclamation.setObjet(dataobj.getString("objet"));
                                reclamation.setContenu(dataobj.getString("contenu"));

                                dataModelArrayList.add(reclamation);

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




    // Start Intent And ListView Setup
    //Function to Select item for EDIT/DELETE/DETAILS
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()) {

            case EDIT:
                break;

            case DElETE:


                break;

            case DETAIL:



        }

        return super.onContextItemSelected(item);
    }


    //FUNCTION TO SETUP THE LIST VIEW
    private void setupListview(){
        supprimerSimpleProgressDialog();
        listReclamationAdapter = new ListReclamationAdapter(this, dataModelArrayList);
        listView.setAdapter(listReclamationAdapter);
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
        } catch (Exception ie) {
            ie.printStackTrace();

        }

    }
    //FUNCTION TO DISPLAY PROGRESS DIAALOG
    public static void AfficherProgressDialog(Context context, String title, String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }
}
