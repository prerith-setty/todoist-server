package in.prerith.todoist.todoserver;


import org.springframework.data.repository.CrudRepository;

public interface TaskRepository  extends CrudRepository<Task, String> {
}
