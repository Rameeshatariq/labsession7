package com.example.meesh.labsession7;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String urlJsonData="https://api.androidhive.info/volley/person_array.json";
    RequestQueue queue=null;
    private ProgressDialog pDialog;
    private ListView listView;
    private ArrayList<DataModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

         listView=(ListView)findViewById(R.id.list);
         list=new ArrayList<>();

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        jsonRequest();
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    public void jsonRequest()
    {
        showpDialog();
        JsonArrayRequest json= new JsonArrayRequest(urlJsonData, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                hidepDialog();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject person = (JSONObject) jsonArray.get(i);

                        String name = person.getString("name");
                        String email = person.getString("email");
                        JSONObject phone = person.getJSONObject("phone");
                        String mobile = phone.getString("mobile");

                        list.add(new DataModel(name, email, mobile));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                final customAdapter adp =new customAdapter(MainActivity.this,R.layout.customlist,list);
                listView.setAdapter(adp);
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                hidepDialog();

            }
        });

        queue.add(json);
    }
}
