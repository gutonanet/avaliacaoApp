package com.lemes.augusto.avaliacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.lemes.augusto.avaliacao.entity.AcaoEnum;
import com.lemes.augusto.avaliacao.entity.MateriaDTO;
import com.lemes.augusto.avaliacao.entity.ProvaDTO;
import com.lemes.augusto.avaliacao.entity.QuestaoDTO;
import com.lemes.augusto.avaliacao.service.ProvaResponseService;
import com.lemes.augusto.avaliacao.service.QuestaoRestPostService;
import com.lemes.augusto.avaliacao.service.RespostaRestPostService;
import com.lemes.augusto.avaliacao.view.SpinnerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuestaoEditActivity extends AppCompatActivity{

    String urlSalvar="http://192.168.0.10:8080/avaliacao/api/salvarQuestao";
    QuestaoDTO questaoDTO;
    Long idQuestao;
    Long idProva;
    EditText habilidade;
    EditText questao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_questao);

        Intent it = getIntent();

        //Recuperei a string da outra activity
        idQuestao = it.getLongExtra("idQuestao", 0L);
        idProva = it.getLongExtra("idProva", 0L);
        String h = it.getStringExtra("habilidade");
        String q = it.getStringExtra("questao");
        habilidade = (EditText)findViewById(R.id.habilidade);
        questao = (EditText)findViewById(R.id.questao);
        habilidade.setText(h);
        questao.setText(q);

        final Button buttonSalvar = findViewById(R.id.button_salvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                habilidade = (EditText)findViewById(R.id.habilidade);
                questao = (EditText)findViewById(R.id.questao);
                Map<String,String> param = new LinkedHashMap<>();
                param.put("habilidade", habilidade.getText().toString());
                param.put("questao", questao.getText().toString());
                param.put("idQuestao", idQuestao == null?"":idQuestao.toString());
                param.put("idProva", idProva == null?"":idProva.toString());
                QuestaoRestPostService exec = new QuestaoRestPostService(param,urlSalvar, QuestaoEditActivity.this, AcaoEnum.SALVAR, questaoDTO);
                exec.callvolly();


            }
        });

        final Button buttonRespostas = findViewById(R.id.button_resposta);
        buttonRespostas.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(idQuestao == null){
                    return;
                }

                Intent myIntent = new Intent(QuestaoEditActivity.this, ListarRespostasActivity.class);
                myIntent.putExtra("idQuestao", idQuestao);
                startActivity(myIntent);
            }
        });


    }

}
