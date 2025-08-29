package com.example.taskapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.taskapi.model.Task;
import com.example.taskapi.model.service.TaskService;
import com.example.taskapi.repository.TaskRepository;

class TaskServiceTest {

    private final TaskRepository repository = mock(TaskRepository.class);
    private final TaskService service = new TaskService();

    {
        service.getClass().getDeclaredFields();
        try {
            var field = service.getClass().getDeclaredField("repository");
            field.setAccessible(true);
            field.set(service, repository);
        } catch (Exception ignored) {}
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setTitle(" New Task Created");
        task.setDueDate(LocalDate.now().plusDays(1));
        when(repository.save(task)).thenReturn(task);

        Task result = service.save(task);
        assertEquals("New Task Created", result.getTitle());
    }

    @Test
    void testFetchTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Fetch the Task");
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = service.getTask(1L);
        assertTrue(result.isPresent());
        assertEquals("Fetch the Task", result.get().getTitle());
    }
}
