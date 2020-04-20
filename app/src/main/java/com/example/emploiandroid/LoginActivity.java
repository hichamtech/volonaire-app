package com.example.emploiandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.emploiandroid.EspaceAdministrator.AdminActivity;
import com.example.emploiandroid.Models.LoginModel;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    //DECLARATION
    private static final String DEBUGTAG = LoginActivity.class.getCanonicalName();
    private static String URL_BASE = "http://192.168.1.11:8000/api/login_check";
    LoginModel loginModel;
    EditText username,password ;
    Button btnLogin;
    //END DECLARATION

    //RECYCLE METHODS
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        username = findViewById(R.id.textUsername);
        password = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
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
    //END RECYCLE METHOD

    //LOGIN FUNCTION
    public void Login() {
        Log.d(DEBUGTAG, "Login");
        String userN = username.getText().toString();
        String pass = password.getText().toString();

       if(TextUtils.isEmpty(userN) )  {
            username.setError("username cannot be empty");
            return;
         }
        if (TextUtils.isEmpty((pass))){
            password.setError("password cannot be empty");
        }
        else {

            Map<String, String> params = new HashMap<String, String>();
            params.put("username", userN);
            params.put("password", pass);

            JsonObjectRequest loginForm = new JsonObjectRequest(com.android.volley.Request.Method.POST,
                    URL_BASE, new JSONObject(params),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(DEBUGTAG, response.toString());
                            try {
                                loginModel = new LoginModel(response.getInt("id"),
                                        response.getString("email"),
                                        response.getString("role"),
                                        response.getString("status"),
                                        response.getString("token")
                                );
                                if (loginModel.getRole().equals("[\"ROLE_ADMIN\"]") && loginModel.getStatus().equals("success")) {

                                        openAdminProfile();
                                    Log.d(DEBUGTAG, "redirect to admin page");

                                } else {
                                    Log.d(DEBUGTAG, "not admin");
                                    return;
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Log.e(DEBUGTAG, error.getMessage());
                    } else if (error instanceof AuthFailureError) {
                        Log.e(DEBUGTAG, error.getMessage());
                    } else if (error instanceof ServerError) {
                        Log.e(DEBUGTAG, error.getMessage());
                    } else if (error instanceof NetworkError) {
                        Log.e(DEBUGTAG, error.getMessage());
                    } else if (error instanceof ParseError) {
                        Log.e(DEBUGTAG, error.getMessage());
                    }
                }


            })/* {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer " + token);
                    return headers;
                }
            }*/;


            VolleySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(loginForm);

        }
    }

    //FUNCTION TO OPEN ADMIN DASH AND SENDING USER INFO
    public void openAdminProfile(){
        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
       intent.putExtra("email",loginModel.getEmail());
        intent.putExtra("token",loginModel.getToken());
        intent.putExtra("id",loginModel.getId());
        startActivity(intent);

    }

    public void btnLogin(View view) {
        if(view == btnLogin){
            Login();
        }
    }
}
