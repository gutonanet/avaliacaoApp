package com.lemes.augusto.avaliacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lemes.augusto.avaliacao.entity.AcaoEnum;
import com.lemes.augusto.avaliacao.entity.QuestaoDTO;
import com.lemes.augusto.avaliacao.entity.RespostaDTO;
import com.lemes.augusto.avaliacao.service.QuestaoRestPostService;
import com.lemes.augusto.avaliacao.service.RespostaRestPostService;

import java.util.LinkedHashMap;
import java.util.Map;

public class ListarRespostasActivity extends AppCompatActivity {

    String urlListar="http://192.168.0.10:8080/avaliacao/api/listarRespostas";

    Long idQuestao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_respostas);

        ListView listaRespostas = (ListView) findViewById(R.id.listaRespostas);

        Intent it = getIntent();

        //Recuperei a string da outra activity
        idQuestao = it.getLongExtra("idQuestao", 0L);
        listarRespostas(idQuestao, listaRespostas);

        final Button botaoIncluir = findViewById(R.id.button_incluir_resposta);
        botaoIncluir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(ListarRespostasActivity.this, RespostaEditActivity.class);
                myIntent.putExtra("idQuestao", idQuestao);

                startActivity(myIntent);


            }
        });



    }


    private void listarRespostas(Long idQuestao,  ListView listaRespostas){
        Map<String,String> param = new LinkedHashMap<>();
        param.put("idQuestao", idQuestao.toString());
        RespostaRestPostService execute = new RespostaRestPostService(param,urlListar,this, AcaoEnum.LISTAR, new RespostaDTO());
        execute.setListView(listaRespostas);
        execute.callvolly();

    }
}
