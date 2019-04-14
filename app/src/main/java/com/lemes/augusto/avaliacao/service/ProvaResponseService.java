package com.lemes.augusto.avaliacao.service;

import android.content.Context;
import android.content.Intent;

import com.lemes.augusto.avaliacao.ListarQuestoesActivity;
import com.lemes.augusto.avaliacao.MainActivity;
import com.lemes.augusto.avaliacao.entity.AcaoEnum;
import com.lemes.augusto.avaliacao.entity.MateriaDTO;
import com.lemes.augusto.avaliacao.entity.ProvaDTO;

import org.json.JSONObject;

import java.util.Map;

public class ProvaResponseService extends ExecuteRestPostService<ProvaDTO> {

    public ProvaResponseService(Map<String, String> params, String url, Context act, AcaoEnum acao, ProvaDTO dto) {
        super(params, url, act,acao, dto);
    }

    @Override
    public void response(String response) {
        if (acao.equals(AcaoEnum.SALVAR)){
            salvar(response);
        }

    }

    private void salvar(String response){
        try {

            JSONObject obj = new JSONObject(response);

            Integer idTipoProva = obj.getInt("idTipoProva");
            Long idProva =  obj.getLong("idProva");;
            Integer idMateria = obj.getInt("idMateria");;
            String turma = obj.getString("turma");
            String frase = obj.getString("turma");

            dto.setId(idProva);
            dto.setTurma(turma);
            dto.setFrase(frase);
            dto.setTipoProva(idTipoProva);
            Intent it = new Intent(act, ListarQuestoesActivity.class);
            it.putExtra("idProva", dto.getId());
            act.startActivity(it);
        } catch (Throwable tx) {
            tx.printStackTrace();
        }
    }
}
