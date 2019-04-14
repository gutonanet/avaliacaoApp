package com.lemes.augusto.avaliacao.service;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lemes.augusto.avaliacao.QuestaoEditActivity;
import com.lemes.augusto.avaliacao.entity.AcaoEnum;
import com.lemes.augusto.avaliacao.entity.MateriaDTO;
import com.lemes.augusto.avaliacao.entity.ProvaDTO;
import com.lemes.augusto.avaliacao.entity.QuestaoDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestaoRestPostService extends ExecuteRestPostService<QuestaoDTO> {

    private List<QuestaoDTO> lista;

    private ListView listView;

    public QuestaoRestPostService(Map<String, String> params, String url, Context act, AcaoEnum acao, QuestaoDTO dto) {
        super(params, url, act,acao, dto);
    }


    @Override
    public void response(String response) {
        if(acao.equals(AcaoEnum.LISTAR)){
            listarQuestoes(response);
        }

    }

    private void listarQuestoes(String response){
        lista = new ArrayList<>();
        try {

            JSONArray array = new JSONArray(response);
            for(int i = 0; i < array.length();i++){
                JSONObject obj = array.getJSONObject(i);
                QuestaoDTO q = new QuestaoDTO();
                q.setId(obj.getLong("idQuestao"));
                q.setHabilidade(obj.getString("habilidade"));
                q.setQuestao(obj.getString("questao"));
                q.setIdProva(obj.getLong("idProva"));
                lista.add(q);
            }


            ArrayAdapter<QuestaoDTO> adapter = new ArrayAdapter<QuestaoDTO>(act,
                    android.R.layout.simple_list_item_1,lista);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            QuestaoDTO questao = (QuestaoDTO) parent.getItemAtPosition(position);

                            if(questao == null){

                            }

                            Intent myIntent = new Intent(view.getContext(), QuestaoEditActivity.class);
                            myIntent.putExtra("idQuestao", questao.getId());
                            myIntent.putExtra("habilidade", questao.getHabilidade());
                            myIntent.putExtra("questao", questao.getQuestao());
                            myIntent.putExtra("idProva", questao.getIdProva());
                            act.startActivity(myIntent);


                        }


                    });

        } catch (Throwable tx) {
            tx.printStackTrace();
        }

    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }
}
