package com.lemes.augusto.avaliacao.service;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lemes.augusto.avaliacao.ListarQuestoesActivity;
import com.lemes.augusto.avaliacao.ListarRespostasActivity;
import com.lemes.augusto.avaliacao.QuestaoEditActivity;
import com.lemes.augusto.avaliacao.RespostaEditActivity;
import com.lemes.augusto.avaliacao.entity.AcaoEnum;
import com.lemes.augusto.avaliacao.entity.QuestaoDTO;
import com.lemes.augusto.avaliacao.entity.RespostaDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RespostaRestPostService extends ExecuteRestPostService<RespostaDTO>  {

    private List<RespostaDTO> lista;

    private ListView listView;

    public RespostaRestPostService(Map<String, String> params, String url, Context act, AcaoEnum acao, RespostaDTO dto) {
        super(params, url, act,acao, dto);
    }


    @Override
    public void response(String response) {
        if (acao.equals(AcaoEnum.LISTAR)) {
            listarRespostas(response);
        }

        if (acao.equals(AcaoEnum.SALVAR)) {
            salvar(response);
        }

        if (acao.equals(AcaoEnum.EXCLUIR)) {
            excluir(response);
        }

    }

    private void listarRespostas(String response){
        lista = new ArrayList<>();
        try {

            JSONArray array = new JSONArray(response);
            for(int i = 0; i < array.length();i++){
                JSONObject obj = array.getJSONObject(i);
                RespostaDTO r = new RespostaDTO();
                r.setId(obj.getLong("idResposta"));
                r.setResposta(obj.getString("resposta"));
                r.setIdQuestao(obj.getLong("idResposta"));
                lista.add(r);
            }


            ArrayAdapter<RespostaDTO> adapter = new ArrayAdapter<RespostaDTO>(act,
                    android.R.layout.simple_list_item_1,lista);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            RespostaDTO resposta = (RespostaDTO) parent.getItemAtPosition(position);

                            if(resposta == null){
                                return;
                            }

                            Intent myIntent = new Intent(view.getContext(), RespostaEditActivity.class);
                            myIntent.putExtra("idQuestao", resposta.getIdQuestao());
                            myIntent.putExtra("resposta", resposta.getResposta());
                            myIntent.putExtra("idResposta", resposta.getId());
                            act.startActivity(myIntent);


                        }


                    });

        } catch (Throwable tx) {
            tx.printStackTrace();
        }

    }

    private void salvar(String response){
        try {

            JSONObject obj = new JSONObject(response);

            Long idQuestao =  obj.getLong("idQuestao");;

            Intent myIntent = new Intent(act, ListarRespostasActivity.class);
            myIntent.putExtra("idQuestao", idQuestao);
            act.startActivity(myIntent);


        } catch (Throwable tx) {
            tx.printStackTrace();
        }
    }

    private void excluir(String response){
        try {

            JSONObject obj = new JSONObject(response);

            Long idQuestao =  obj.getLong("idQuestao");;

            Intent myIntent = new Intent(act, ListarRespostasActivity.class);
            myIntent.putExtra("idQuestao", idQuestao);
            act.startActivity(myIntent);


        } catch (Throwable tx) {
            tx.printStackTrace();
        }
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }
}


