package com.lemes.augusto.avaliacao.entity;

import java.util.List;

public class ProvaDTO {

    private Long id;

    private String turma;


    private MateriaDTO materia;

    private Integer tipoProva;

    private String frase;

    private List<QuestaoDTO> questoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public MateriaDTO getMateria() {
        return materia;
    }

    public void setMateria(MateriaDTO materia) {
        this.materia = materia;
    }

    public Integer getTipoProva() {
        return tipoProva;
    }

    public void setTipoProva(Integer tipoProva) {
        this.tipoProva = tipoProva;
    }

    public List<QuestaoDTO> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoDTO> questoes) {
        this.questoes = questoes;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }
}
