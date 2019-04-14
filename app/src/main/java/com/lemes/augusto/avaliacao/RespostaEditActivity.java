package com.lemes.augusto.avaliacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lemes.augusto.avaliacao.entity.AcaoEnum;
import com.lemes.augusto.avaliacao.entity.QuestaoDTO;
import com.lemes.augusto.avaliacao.entity.RespostaDTO;
import com.lemes.augusto.avaliacao.service.QuestaoRestPostService;
import com.lemes.augusto.avaliacao.service.RespostaRestPostService;

import java.util.LinkedHashMap;
import java.util.Map;

public class RespostaEditActivity extends AppCompatActivity{

    String urlSalvar="http://192.168.0.10:8080/avaliacao/api/salvarResposta";
    RespostaDTO respostaDTO;
    Long idQuestao;
    Long idResposta;
    EditText resposta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_resposta);

        Intent it = getIntent();

        //Recuperei a string da outra activity
        idQuestao = it.getLongExtra("idQuestao", 0L);
        idResposta = it.getLongExtra("idResposta", 0L);
        String r = it.getStringExtra("resposta");
        resposta = (EditText)findViewById(R.id.resposta);
        resposta.setText(r);


        final Button buttonSalvar = findViewById(R.id.button_salvar_resposta);
        buttonSalvar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                resposta = (EditText)findViewById(R.id.resposta);
                Map<String,String> param = new LinkedHashMap<>();
                param.put("resposta", resposta.getText().toString());
                param.put("idQuestao", idQuestao == null?"":idQuestao.toString());
                param.put("idResposta", idResposta == null?"":idResposta.toString());
                RespostaRestPostService exec = new RespostaRestPostService(param,urlSalvar, RespostaEditActivity.this, AcaoEnum.SALVAR, respostaDTO);
                exec.callvolly();


            }
        });

    }

}
