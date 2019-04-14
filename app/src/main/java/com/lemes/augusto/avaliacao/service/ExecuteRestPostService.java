package com.lemes.augusto.avaliacao.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lemes.augusto.avaliacao.entity.AcaoEnum;

import java.util.Map;

public abstract class ExecuteRestPostService<T> {

    private Map<String, String> params;
    private String url;
    protected Context act;
    protected T dto;
    protected AcaoEnum acao;

    private ExecuteRestPostService(){

    }

    public ExecuteRestPostService(Map<String, String> params,String url, Context act, AcaoEnum acao, T dto){
        this.params = params;
        this.url = url;
        this.act = act;
        this.dto = dto;
        this.acao = acao;
    }

    public void callvolly(){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(act);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response(response);


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

    public abstract void response(String response);

}
