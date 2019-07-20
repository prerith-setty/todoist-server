package in.prerith.todoist.todoserver;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )    private String taskId;

    private String taskName;
    private String taskDescription;


    @Column(columnDefinition = "boolean default false")
    private boolean isComplete=false;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isComplete() == task.isComplete() &&
            Objects.equals(getTaskId(), task.getTaskId()) &&
            Objects.equals(getTaskName(), task.getTaskName()) &&
            Objects.equals(getTaskDescription(), task.getTaskDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getTaskName(), getTaskDescription(), isComplete());
    }
}
