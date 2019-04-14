package com.lemes.augusto.avaliacao.entity;

public class RespostaDTO {

    private Long id;

    private String resposta;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResposta() {
        return resposta;
    }

    public String getRespostaFormatted(Integer indice) {
        if(resposta == null || "".equals(resposta)) {
            return "";
        }
        String marcador = "";
        switch(indice) {
            case 1: marcador = "a) ";break;
            case 2: marcador = "b) ";break;
            case 3: marcador = "c) ";break;
            case 4: marcador = "d) ";break;
            case 5: marcador = "e) ";break;
        }

        return marcador+resposta;
    }

    public String getRespostaShort() {
        if(resposta!= null) {
            if(resposta.length() > 100) {
                return resposta.substring(0, 100)+"...";
            }
        }
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

}
