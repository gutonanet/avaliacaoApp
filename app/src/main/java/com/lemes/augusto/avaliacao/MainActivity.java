package com.lemes.augusto.avaliacao;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
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
import com.lemes.augusto.avaliacao.service.ExecuteRestPostService;
import com.lemes.augusto.avaliacao.service.ProvaResponseService;
import com.lemes.augusto.avaliacao.view.SpinnerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Spinner spinnerMaterias;
    String urlTipoProva="https://avaliacao1.herokuapp.com/api/tiposProva";
    String urlMaterias="https://avaliacao1.herokuapp.com/api/materias";
    String urlSalvar="https://avaliacao1.herokuapp.com/api/findProva";
    ArrayList<String> listaTipoProva;
    ArrayList<String> listaMateria;
    Button button;

    public static String titulo = "";



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
                EditText turmaE = (EditText)findViewById(R.id.turma);
                EditText frase = (EditText)findViewById(R.id.frase);
                Map<String,String> param = new LinkedHashMap<>();
                param.put("turma", turmaE.getText().toString());
                param.put("tipoProva", spinner.getSelectedItem().toString());
                param.put("frase", frase.getText().toString());
                param.put("materia",spinnerMaterias.getSelectedItem().toString());

                titulo = spinner.getSelectedItem().toString()+", "+spinnerMaterias.getSelectedItem().toString()+ ", "+ turmaE.getText().toString();


                ProvaDTO prova = new ProvaDTO();
                MateriaDTO material = new MateriaDTO();
                prova.setMateria(material);
                ProvaResponseService exec = new ProvaResponseService(param,urlSalvar, getThis(), AcaoEnum.SALVAR, prova);
                exec.callvolly();


            }
        });

    }

    public Context getThis(){
        return this;
    }



}
