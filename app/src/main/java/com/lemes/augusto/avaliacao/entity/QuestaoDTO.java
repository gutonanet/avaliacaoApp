package com.lemes.augusto.avaliacao.entity;

import java.util.ArrayList;
import java.util.List;

public class QuestaoDTO {

    private Long id;

    private String questao;

    private String habilidade;

    private Integer tipoQuestao;

    private String resposta;

    private List<RespostaDTO> respostas = new ArrayList<>();

    public String getRespostaShort() {
        if(resposta!= null) {
            if(resposta.length() > 100) {
                return resposta.substring(0, 100)+"...";
            }
        }
        return resposta;
    }

    public String getQuestaoShort() {
        if(questao!= null) {
            if(questao.length() > 100) {
                return questao.substring(0, 100)+"...";
            }
        }
        return questao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public Integer getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(Integer tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public List<RespostaDTO> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaDTO> respostas) {
        this.respostas = respostas;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

}
