package in.prerith.todoist.todoserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class TaskController {

    List<Task> tasks = new ArrayList<>();

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/listTasks")
    public List<Task> listTasks() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @PostMapping("/updateTask")
    public Task updateTask(@RequestBody Task inputTask) {
        Task updatedTask = taskRepository.save(inputTask);
        return updatedTask;
    }

    @PostMapping("/createTask")
    public Task createTask(@RequestBody Task inputTask) {
        inputTask.setTaskId(UUID.randomUUID().toString());
        taskRepository.save(inputTask);
//        tasks.add(inputTask);
        return inputTask;
    }

    @PostMapping("/completeTask")
    public Task completeTask(@RequestParam String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("No such task"));
        task.setComplete(true);
        taskRepository.save(task);
        return task;
    }
}
