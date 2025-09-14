package com.sostenes.task_manager.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Resposta HTTP", description = "Os códigos HTTP permitidos são: '200' representa OK, '201' representa Criado, '202' representa Aceito, '204' representa Sem Conteúdo, '400' representa Requisição Ruim, '401' representa Não Autorizado, '403' representa Proibido, '404' representa Não Encontrado, '500' representa Erro Interno do Servidor, '501' representa Não Implementado.", implementation = RespostaHttpEnum.class)
public enum RespostaHttpEnum {

    OK(200, "OK"),
    CREATED(201, "Criado"),
    ACCEPTED(202, "Aceito"),
    NO_CONTENT(204, "Sem Conteúdo"),
    BAD_REQUEST(400, "Requisição Ruim"),
    UNAUTHORIZED(401, "Não Autorizado"),
    FORBIDDEN(403, "Proibido"),
    NOT_FOUND(404, "Não Encontrado"),
    INTERNAL_SERVER_ERROR(500, "Erro Interno do Servidor"),
    NOT_IMPLEMENTED(501, "Não Implementado");

    private final int codigoHttp;

    private final String descricaoHttp;

    RespostaHttpEnum(int code, String description) {

        this.codigoHttp = code;
        this.descricaoHttp = description;
    }

    public int getCodigoHttp() {

        return codigoHttp;
    }

    public String getDescricaoHttp() {

        return descricaoHttp;
    }

    @JsonCreator
    public static RespostaHttpEnum forValue(int valor) {

        for (RespostaHttpEnum entry : values()) {

            if (entry.codigoHttp == valor) {

                return entry;
            }
        }

        throw new IllegalArgumentException("Valor desconhecido: " + valor);
    }
}