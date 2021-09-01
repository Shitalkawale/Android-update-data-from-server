package com.example.updatedatafromserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText nameedt,nameupdate,emailupdate,mobileupdate;
    Button btnupdate,btnview;
    String web_url="https://shitalkawale.000webhostapp.com/Update.php";
    String web_url1="https://shitalkawale.000webhostapp.com/viewSingleRecord.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameedt=findViewById(R.id.edtname);
        nameupdate=findViewById(R.id.upname);
        emailupdate=findViewById(R.id.upemail);
        mobileupdate=findViewById(R.id.upphone);

    }

    public void viewData(View view) {
        viewDataServer(nameedt.getText().toString());
    }

    public void updateData(View view) {

        updateDataServer(nameedt.getText().toString());
    }

    public void updateDataServer(String name)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, web_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            int sucess=jsonObject.getInt("sucess");
                            if(sucess==1)
                            {
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map=new HashMap<>();
                map.put("Name",name);
                map.put("name",nameupdate.getText().toString());
                map.put("email",emailupdate.getText().toString());
                map.put("mobile",mobileupdate.getText().toString());


                return map;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void viewDataServer(String name)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, web_url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            int sucess=jsonObject.getInt("sucess");
                            if(sucess==1)
                            {
                                nameupdate.setText(jsonObject.getString("name"));
                                emailupdate.setText(jsonObject.getString("email"));
                                mobileupdate.setText(jsonObject.getString("mobile"));


                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                            }
                            else if(sucess==2)
                            {
                                nameupdate.setText("");
                                emailupdate.setText("");
                                mobileupdate.setText("");
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("Name",name);

                return  map;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}