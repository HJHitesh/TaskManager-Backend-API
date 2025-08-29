package com.example.taskapi.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskapi.model.Status;
import com.example.taskapi.model.Task;
import com.example.taskapi.model.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
@Tag(name = "Task API", description = "Operations related to tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    // ------------------ CREATE ------------------
    @Operation(summary = "Create a new task")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Task created successfully"),
        @ApiResponse(responseCode = "400", description = "Validation error",
                     content = @Content(schema = @Schema(implementation = Map.class)))
    })
    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return service.save(task);
    }

    // ------------------ GET ALL ------------------
    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of tasks retrieved successfully")
    })
    @GetMapping
    public Page<Task> getAllTask(@RequestParam(required = false) Status status,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return service.getTasks(status, pageable);
    }

    // ------------------ GET BY ID ------------------
    @Operation(summary = "Get task by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found",
                     content = @Content(schema = @Schema(implementation = Map.class)))
    })
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return service.getTask(id)
                      .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // ------------------ UPDATE ------------------
    @Operation(summary = "Update an existing task")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task updated successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found",
                     content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "400", description = "Validation error",
                     content = @Content(schema = @Schema(implementation = Map.class)))
    })
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        return service.update(id, task);
    }

    // ------------------ DELETE ------------------
    @Operation(summary = "Delete a task by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task deleted successfully",
                     content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "404", description = "Task not found",
                     content = @Content(schema = @Schema(implementation = Map.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of(
                "message", "Task deleted successfully",
                "id", id
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "message", "Task not found",
                "id", id
            ));
        }
    }
}
