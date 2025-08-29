package com.example.taskapi.model.service;

import com.example.taskapi.model.Task;
import com.example.taskapi.model.Status;
import com.example.taskapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
	
    @Autowired
    private TaskRepository repo;

    public Task save(Task task) { return repo.save(task); }

    public Page<Task> getTasks(Status status, Pageable pageable) {
        return (status != null) ? repo.findByStatus(status, pageable) : repo.findAll(pageable);
    }

    public Optional<Task> getTask(Long id) { return repo.findById(id); }

    public Task update(Long id, Task updated) {
        return repo.findById(id).map(task -> {
            task.setTitle(updated.getTitle());
            task.setDescription(updated.getDescription());
            task.setDueDate(updated.getDueDate());
            task.setStatus(updated.getStatus());
            return repo.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public boolean delete(Long id) { 
    	 if (repo.existsById(id)) {
    	        repo.deleteById(id);
    	        return true;
    	    } else {
    	        return false;
    	    }
    }
}

