package com.example.meesh.labsession7;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        ListView listView=(ListView)findViewById(R.id.list);
        final ArrayList<DataModel> list=new ArrayList<>();
        JsonArrayRequest json= new JsonArrayRequest(urlJsonData, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

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
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });


        final customAdapter adp =new customAdapter(this,R.layout.customlist,list);
        listView.setAdapter(adp);
        queue.add(json);
    }
}
