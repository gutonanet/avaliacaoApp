package com.lemes.augusto.avaliacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.lemes.augusto.avaliacao.entity.AcaoEnum;
import com.lemes.augusto.avaliacao.entity.QuestaoDTO;
import com.lemes.augusto.avaliacao.service.QuestaoRestPostService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListarQuestoesActivity extends AppCompatActivity{

    String urlListar="http://192.168.0.10:8080/avaliacao/api/listarQuestoes";
    Long idProva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_questoes);

        ListView listaQuestoes = (ListView) findViewById(R.id.listaQuestoes);

        Intent it = getIntent();

        //Recuperei a string da outra activity
        idProva = it.getLongExtra("idProva", 0L);
        listarQuestoes(idProva, listaQuestoes);

        final Button botaoIncluir = findViewById(R.id.button_incluir_questao);
        botaoIncluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(ListarQuestoesActivity.this, QuestaoEditActivity.class);
                myIntent.putExtra("idProva", idProva);

                startActivity(myIntent);

            }
        });

    }


    private void listarQuestoes(Long idProva,  ListView listaQuestoes){
        Map<String,String> param = new LinkedHashMap<>();
        param.put("idProva", idProva.toString());
        QuestaoRestPostService execute = new QuestaoRestPostService(param,urlListar,this, AcaoEnum.LISTAR, new QuestaoDTO());
        execute.setListView(listaQuestoes);
        execute.callvolly();

    }
}
