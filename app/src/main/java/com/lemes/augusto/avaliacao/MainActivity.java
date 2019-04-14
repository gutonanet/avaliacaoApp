package com.lemes.augusto.avaliacao;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.lemes.augusto.avaliacao.entity.ProvaDTO;
import com.lemes.augusto.avaliacao.service.ExecuteRestPostService;
import com.lemes.augusto.avaliacao.service.ProvaResponseService;
import com.lemes.augusto.avaliacao.view.SpinnerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Spinner spinnerMaterias;
    String urlTipoProva="http://192.168.0.10:8080/avaliacao/api/tiposProva";
    String urlMaterias="http://192.168.0.10:8080/avaliacao/api/materias";
    String urlSalvar="http://192.168.0.10:8080/avaliacao/api/findProva";
    ArrayList<String> listaTipoProva;
    ArrayList<String> listaMateria;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTipoProva=new ArrayList<>();
        spinner=(Spinner)findViewById(R.id.tipoProva);
        SpinnerFactory sf1 = new SpinnerFactory(urlTipoProva, listaTipoProva, spinner, MainActivity.this, getApplicationContext());
        spinner.setOnItemSelectedListener(sf1);

        listaMateria=new ArrayList<>();
        spinnerMaterias=(Spinner)findViewById(R.id.materia);
        SpinnerFactory sf2 = new SpinnerFactory(urlMaterias, listaMateria, spinnerMaterias, MainActivity.this, getApplicationContext());
        spinnerMaterias.setOnItemSelectedListener(sf2);

        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                EditText turma = (EditText)findViewById(R.id.turma);
                EditText frase = (EditText)findViewById(R.id.frase);
                Map<String,String> param = new LinkedHashMap<>();
                param.put("turma", turma.getText().toString());
                param.put("tipoProva", spinner.getSelectedItem().toString());
                param.put("frase", frase.getText().toString());
                param.put("materia",spinnerMaterias.getSelectedItem().toString());
                ProvaDTO provaDTO = new ProvaDTO();
                ProvaResponseService exec = new ProvaResponseService(param,urlSalvar, MainActivity.this,provaDTO);
                exec.callvolly();

            }
        });

    }



}
