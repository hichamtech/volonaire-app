package com.example.emploiandroid.EspaceAdministrator.Demandeurs;

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
import com.example.emploiandroid.EspaceAdministrator.ListAdapter;
import com.example.emploiandroid.Models.Personne;
import com.example.emploiandroid.Models.URLs;
import com.example.emploiandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListDemandeurActivity extends AppCompatActivity {


    private static final String DEBUGTAG = ListDemandeurActivity.class.getCanonicalName();

    private static ProgressDialog mProgressDialog;
    private ListView listView;
    private  ArrayList<Personne> dataModelArrayList;
    private ListAdapter listAdapter;
    private Personne moniteur;
    private Button btnAddMoniteur;
    int lockClikedItemIndex;
    private  JSONArray dataArray;
    private static  final  int EDIT = 0,DElETE=1, DETAIL =2;
    private JSONObject dataobj;
    int idMoniteur;
    private URLs urLs;
    //TO SEND DATA to the OTHERS ACTIVITY SEE FUNCTION ItemLongClick
    private String nom,prenom,email,cin,adresse,tele;
    private Date dateN;
    //Start RCYCLE METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_moniteur);
        urLs = new URLs();
        listView = findViewById(R.id.lv);
        btnAddMoniteur = findViewById(R.id.btnAddMoniteur);
        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                idMoniteur = dataModelArrayList.get(position).getId();
                nom = dataModelArrayList.get(position).getNom();
                prenom = dataModelArrayList.get(position).getPrenom();
                email = dataModelArrayList.get(position).getEmail();
                adresse = dataModelArrayList.get(position).getAdresse();
                tele = dataModelArrayList.get(position).getNumTelephone();
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
        getListDemandeurs();

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

    //CRUD Demandeurs

    private void getListDemandeurs(){
        AfficherProgressDialog(this, "Chargement..","Recherche Demandeurs",false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urLs.URL_LISTEDEMANDEUR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        dataModelArrayList = new ArrayList<>();

                        try {
                            //if(obj.optString("status").equals("true")){ //TODO:ADD STATUS OF RESPONSE IN API
                            JSONObject obj = new JSONObject(response);
                            dataArray  = obj.getJSONArray("hydra:member");
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

                            for (int i = 0; i < dataArray.length(); i++)
                            {
                                moniteur = new Personne();

                                dataobj = dataArray.getJSONObject(i);
                                moniteur.setId(dataobj.getInt("id"));
                                moniteur.setNom(dataobj.getString("nom"));
                                moniteur.setPrenom(dataobj.getString("prenom"));
                                moniteur.setAdresse(dataobj.getString("adresse"));
                                moniteur.setEmail(dataobj.getString("email"));
                                moniteur.setNumTelephone(dataobj.getString("NumTelephone"));
                                dataModelArrayList.add(moniteur);

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
    //Function To Edit Demandeurs
    public void EditDemandeur(int idDemandeur){
        Intent intent = new Intent(ListDemandeurActivity.this, EditDemandeurActivity.class);
        intent.putExtra("idDemandeur",idDemandeur);
        intent.putExtra("nom",nom);
        intent.putExtra("prenom",prenom);
        intent.putExtra("email",email);
        intent.putExtra("adresse",adresse);
        intent.putExtra("tel",tele);
        startActivity(intent);

    }
    //Function To Delete Demandeur
    public void DeleteDemandeur(int idDemandeur){



        String url = urLs.URL_BASE_PERSONNE+idDemandeur;
        StringRequest dr = new StringRequest(Request.Method.DELETE, url.trim(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(getApplicationContext(), "Supprimer avec succès", Toast.LENGTH_LONG).show();
                        getListDemandeurs();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.
                        Toast.makeText(getApplicationContext(), "Ajouté avec Succès", Toast.LENGTH_LONG).show();

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(dr);


    }
    //Function to get Detail Demanduer
    public void DetailDemandeur(int idDemandeur){

        Intent intent = new Intent(ListDemandeurActivity.this, DetailDemandeurActivity.class);
        intent.putExtra("idDemandeur",idDemandeur);
        //TODO:ADD API TOKEN
        startActivity(intent);




    }
    //END CRUD Moniteur


    // Start Intent And ListView Setup
    //Function to Select item for EDIT/DELETE/DETAILS
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()) {

            case EDIT:
                EditDemandeur(idMoniteur);
                break;

            case DElETE:
                AlertDialog diaBox = AskOption();
                diaBox.show();

                break;

            case DETAIL:

                DetailDemandeur(idMoniteur);


        }

        return super.onContextItemSelected(item);
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox;
        myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Supprimer")
                .setMessage("Etes vous sur ?")
                //.setIcon(R.drawable.delete) //TODO add icon

                .setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        DeleteDemandeur(idMoniteur);
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }
    //FUNCTION TO SETUP THE LIST VIEW
    private void setupListview(){
        supprimerSimpleProgressDialog();
        listAdapter = new ListAdapter(this, dataModelArrayList);
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
    //FUNCTION TO OPEN FORM Moniteur
    public void OpenAddForm(){

        Intent intent = new Intent(ListDemandeurActivity.this, AddDemandeurFormActivity.class);
        //TODO:SEND TOKEN
        startActivity(intent);

    }
    //OnClick BtnAdd
    public void btnAddMoniteur(View view) {

        if (view == btnAddMoniteur){
            OpenAddForm();
        }

    }
    //End Start Intent ListView Setup


}
