package in.prerith.todoist.todoserver;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class TaskController {

    List<Task> tasks = new ArrayList<>();

    @GetMapping("/listTasks")
    public List<Task> listTasks() {
        return tasks;
    }

    @PostMapping("/updateTask")
    public Task updateTask(@RequestBody Task inputTask) {
        String inputTaskId = inputTask.getTaskId();
        for (Task taskTemp : tasks) {
              if (inputTaskId!=null && taskTemp.getTaskId()!=null && taskTemp.getTaskId().equals(inputTaskId)) {
                taskTemp.setTaskDescription(inputTask.getTaskDescription());
                taskTemp.setTaskName(inputTask.getTaskName());
                taskTemp.setComplete(inputTask.isComplete());
                break;
            }
        }
        return inputTask;
    }

    @PostMapping("/createTask")
    public Task createTask(@RequestBody Task inputTask) {
        inputTask.setTaskId(UUID.randomUUID().toString());
        tasks.add(inputTask);
        return inputTask;
    }

    @PostMapping("/completeTask")
    public Task completeTask(@RequestParam String taskId) {
        Task taskTemp = null;
        for (Task temp : tasks) {
            if (taskId!=null && temp.getTaskId()!=null && temp.getTaskId().equals(taskId)) {
                temp.setComplete(true);
                taskTemp=temp;
                break;
            }
        }
        return taskTemp;
    }
}
