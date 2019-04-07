package com.lemes.augusto.avaliacao.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lemes.augusto.avaliacao.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpinnerFactory implements AdapterView.OnItemSelectedListener {

    private Spinner s;

    private ArrayList<String> l;

    private Context a;

    private Context ac;


    public SpinnerFactory(String url, ArrayList<String> lista, Spinner spinner, Context active, Context applicationContext) {
        s = spinner;
        l = lista;
        a = active;
        ac = applicationContext;
        RequestQueue requestQueue= Volley.newRequestQueue(ac);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String country=jsonObject1.getString("nome");
                        l.add(country);
                    }

                    s.setAdapter(new ArrayAdapter(a, android.R.layout.simple_spinner_dropdown_item, l));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String nome=   s.getItemAtPosition(s.getSelectedItemPosition()).toString();
       // Toast.makeText(ac,nome,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // DO Nothing here
    }
}
