package com.sostenes.task_manager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sostenes.task_manager.domain.Task;
import com.sostenes.task_manager.enums.RespostaHttpEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespostaControllerDTO {

    @Schema(description = "Código de status HTTP da resposta.", example = "400")
    private RespostaHttpEnum resposta;

    @Schema(description = "Mensagem descritiva detalhando o motivo da falha na requisição.", example = "Não foi possível processar a requisição com sucesso !")
    private String mensagem;

    private List<Task> task;

    public RespostaHttpEnum getResposta() {
        return resposta;
    }

    public void setResposta(RespostaHttpEnum resposta) {
        this.resposta = resposta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
