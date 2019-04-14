package com.lemes.augusto.avaliacao.service;

import android.content.Context;

import com.lemes.augusto.avaliacao.entity.ProvaDTO;

import org.json.JSONObject;

import java.util.Map;

public class ProvaResponseService extends ExecuteRestPostService<ProvaDTO> {

    public ProvaResponseService(Map<String, String> params, String url, Context act, ProvaDTO dto) {
        super(params, url, act, dto);
    }

    @Override
    public void response(String response) {
        try {

            JSONObject obj = new JSONObject(response);

            Integer idTipoProva = obj.getInt("idTipoProva");

            Long idProva =  obj.getLong("idProva");;

            Long idMateria = obj.getLong("idMateria");;

            String turma = obj.getString("turma");

            String frase = obj.getString("turma");




        } catch (Throwable tx) {
            tx.printStackTrace();
        }
    }
}
