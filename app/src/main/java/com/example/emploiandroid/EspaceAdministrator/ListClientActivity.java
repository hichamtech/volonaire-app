package com.example.emploiandroid.EspaceAdministrator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.emploiandroid.Models.Personne;
import com.example.emploiandroid.R;

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
    int lockClikedItemIndex;
    private static  final  int EDIT = 0,DElETE=1, DETAIL =2;

    //Start RCYCLE METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);

        listView = findViewById(R.id.lv);
        btnAddClient = findViewById(R.id.btnAddClient);
        registerForContextMenu(listView);
        getListClients();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
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

    //CRUD CLIENT
    //Function To the list of Client
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
                                client.setId(dataobj.getInt("id"));

                                lockClikedItemIndex = client.getId();
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
    //Function To Edit Client
    public void EditClient(){

    }
    //Function To Delete Client
    public void DeleteClient(int idClient){


    }
    //Function to get Detail CLient
    public void DetailClient(){
        Intent intent = new Intent(ListClientActivity.this, DetailClientActivity.class);
        intent.putExtra("idClient",lockClikedItemIndex);
        //TODO:ADD API TOKEN
        startActivity(intent);




    }
    //END CRUD CLIENT

    // Start Intent And ListView Setup
    //Function to Select item for EDIT/DELETE/DETAILS
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()) {

            case EDIT:

                break;

            case DElETE:


                break;

            case DETAIL:


                Log.d(DEBUGTAG,"this is my id" + lockClikedItemIndex);
                DetailClient();


        }

        return super.onContextItemSelected(item);
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
    public static void AfficherProgressDialog(Context context, String title, String msg, boolean isCancelable) {
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
    //FUNCTION TO OPEN FORM CLIENT
    public void OpenAddForm(){

        Intent intent = new Intent(ListClientActivity.this, AddClientFormActivity.class);
        //TODO:SEND TOKEN
        startActivity(intent);

    }
    //OnClick BtnAdd
    public void btnAddClient(View view) {

        if (view ==btnAddClient){
            OpenAddForm();
        }

    }
    //End Start Intent ListView Setup


}
