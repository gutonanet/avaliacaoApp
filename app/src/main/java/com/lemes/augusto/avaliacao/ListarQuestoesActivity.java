package com.lemes.augusto.avaliacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lemes.augusto.avaliacao.entity.AcaoEnum;
import com.lemes.augusto.avaliacao.entity.QuestaoDTO;
import com.lemes.augusto.avaliacao.service.QuestaoRestPostService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListarQuestoesActivity extends AppCompatActivity{

    String urlListar="http://192.168.0.10:8080/avaliacao/api/listarQuestoes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_questoes);

        ListView listaQuestoes = (ListView) findViewById(R.id.listaQuestoes);

        Intent it = getIntent();

        //Recuperei a string da outra activity
        Long idProva = it.getLongExtra("idProva", 0L);
        listarQuestoes(idProva, listaQuestoes);



    }


    private void listarQuestoes(Long idProva,  ListView listaQuestoes){
        Map<String,String> param = new LinkedHashMap<>();
        param.put("idProva", idProva.toString());
        QuestaoRestPostService execute = new QuestaoRestPostService(param,urlListar,this, AcaoEnum.LISTAR, new QuestaoDTO());
        execute.setListView(listaQuestoes);
        execute.callvolly();

    }
}
