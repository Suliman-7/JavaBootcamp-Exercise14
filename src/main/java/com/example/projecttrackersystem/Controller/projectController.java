package com.example.projecttrackersystem.Controller;

import com.example.projecttrackersystem.Api.ApiResponse;
import com.example.projecttrackersystem.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")


public class projectController {

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/projects")
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @PostMapping("/post")
    public ResponseEntity addProject(@Valid @RequestBody Project project , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @Valid @RequestBody Project project , Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if(index > projects.size()) {
            return ResponseEntity.status(400).body("Project not found");
        }

        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {

        if(index>=projects.size()) {
            return ResponseEntity.status(404).body("Project not found");
        }
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully"));
    }

    @PutMapping("/changestatus/{index}")
    public ResponseEntity changeStatus(@PathVariable int index) {
        if(index>=projects.size()) {
            return ResponseEntity.status(404).body("Project not found");
        }
        if(projects.get(index).getStatus().equalsIgnoreCase("Not Started")) {
            projects.get(index).setStatus("in Progress");
            return ResponseEntity.status(200).body(new ApiResponse("status changed successfully"));
        }else if(projects.get(index).getStatus().equalsIgnoreCase("in Progress")) {
            projects.get(index).setStatus("Completed");
            return ResponseEntity.status(200).body(new ApiResponse("status changed successfully"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("status already completed"));
    }

    @GetMapping("search/{title}")
    public ResponseEntity searchProject(@PathVariable String title) {
        for(Project project : projects) {
            if(project.getTitle().equals(title)) {
                return ResponseEntity.status(200).body(project);
            }
        }
        return ResponseEntity.status(400).body("Project not found");
    }

    @GetMapping("displaybycompany/{companyName}")
    public ResponseEntity displayByCompanyProject(@PathVariable String companyName) {
        ArrayList<Project> companyProjects = new ArrayList<>();
        for(Project project : projects) {
            if(project.getCompanyName().equals(companyName)) {
                companyProjects.add(project);
            }
        }
        if(companyProjects.size()>0) {
            return ResponseEntity.status(200).body(companyProjects);
        }else{
            return ResponseEntity.status(404).body("Project not found");
        }
    }








}
