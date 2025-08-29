package com.example.taskapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskapi.model.Status;
import com.example.taskapi.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	Page<Task> findByStatus(Status status, Pageable pageable);
}
