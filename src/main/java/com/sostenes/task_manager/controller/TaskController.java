package com.sostenes.task_manager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sostenes.task_manager.domain.Task;
import com.sostenes.task_manager.dto.RespostaControllerDTO;
import com.sostenes.task_manager.dto.TaskDTO;
import com.sostenes.task_manager.enums.RespostaHttpEnum;
import com.sostenes.task_manager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(produces = "application/json")
    @Operation(description = "Retornar listagem de todas as tasks", responses = { @ApiResponse(responseCode = "200", description = "Task(s) encontrada com sucesso", content = @Content(schema = @Schema(implementation = Task.class))), @ApiResponse(responseCode = "400", description = "Erro na consulta", content = @Content(schema = @Schema(implementation = RespostaControllerDTO.class))) })
    public ResponseEntity<String> getTasks() throws JsonProcessingException {

        List<Task> tasks =  taskService.findAllTasks();

        RespostaControllerDTO resposta = new RespostaControllerDTO();

        ObjectMapper objectMapper = new ObjectMapper();

        if(!tasks.isEmpty()){

            resposta.setResposta(RespostaHttpEnum.OK);
            resposta.setMensagem("Task(s) encontrada com sucesso");
            resposta.setTask(tasks);

            return ResponseEntity.status(resposta.getResposta().getCodigoHttp()).contentType(MediaType.APPLICATION_JSON).body(objectMapper.writeValueAsString(resposta));

        } else {

            resposta.setResposta(RespostaHttpEnum.BAD_REQUEST);
            resposta.setMensagem("Erro na consulta");

            return ResponseEntity.status(resposta.getResposta().getCodigoHttp()).contentType(MediaType.APPLICATION_JSON).body(objectMapper.writeValueAsString(resposta));
        }
    }

    @PostMapping(produces = "application/json")
    @Operation(description = "Realiza a criação de uma nova task", responses = { @ApiResponse(responseCode = "201", description = "Task criada com sucesso", content = @Content(schema = @Schema(implementation = RespostaControllerDTO.class))), @ApiResponse(responseCode = "400", description = "Erro na criação da task", content = @Content(schema = @Schema(implementation = RespostaControllerDTO.class))) })
    public ResponseEntity<String> createTask(@Parameter (description = "Dados da Task para criação", required = true)@RequestBody TaskDTO task) throws JsonProcessingException {

        RespostaHttpEnum taskCriada = taskService.createTask(task);

        ObjectMapper objectMapper = new ObjectMapper();

        RespostaControllerDTO resposta = new RespostaControllerDTO();

        if(RespostaHttpEnum.CREATED.equals(taskCriada)){

            resposta.setResposta(RespostaHttpEnum.CREATED);
            resposta.setMensagem("Task criada com sucesso");

            return ResponseEntity.status(taskCriada.getCodigoHttp()).contentType(MediaType.APPLICATION_JSON).body(objectMapper.writeValueAsString(resposta));

        } else {

            resposta.setResposta(RespostaHttpEnum.BAD_REQUEST);
            resposta.setMensagem("Erro na criação da task");

            return ResponseEntity.status(taskCriada.getCodigoHttp()).contentType(MediaType.APPLICATION_JSON).body(objectMapper.writeValueAsString(resposta));
        }
    }
}
