package com.lemes.augusto.avaliacao.service;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class ExecuteRestPostService {

    private Map<String, String> params;
    private String url;
    private Context act;


    public ExecuteRestPostService(Map<String, String> params,String url, Context act){
        this.params = params;
        this.url = url;
        this.act = act;
    }

    public void callvolly(){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(act);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
            }
        }) {
            protected Map<String, String> getParams() {
                return params;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }

}
