package com.sostenes.task_manager.service;

import com.sostenes.task_manager.domain.Task;
import com.sostenes.task_manager.dto.TaskDTO;
import com.sostenes.task_manager.enums.RespostaHttpEnum;
import com.sostenes.task_manager.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks(){

        return taskRepository.findAll();
    }

    public RespostaHttpEnum createTask(TaskDTO taskDTO) {

        try {

            Task task = new Task();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setCompleted(false);

            taskRepository.save(task);

            return RespostaHttpEnum.CREATED;

        } catch (Exception e) {

            logger.error("Erro ao salvar a task", e);

            return RespostaHttpEnum.BAD_REQUEST;
        }
    }

}