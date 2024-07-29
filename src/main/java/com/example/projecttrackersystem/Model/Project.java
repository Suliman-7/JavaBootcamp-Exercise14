package com.example.projecttrackersystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class Project {

    @NotEmpty(message = "Id should be not empty ")
    @Size(min = 3 , message = "Id must be more than 2 characters")
    private String id;

    @NotEmpty(message = "title should be not empty")
    @Size(min = 9 , message = "title should be more than 8 characters")
    private String title;

    @NotEmpty(message = "description should be not empty")
    @Size(min = 16 , message = "description length should be more than 15 characters")
    private String description;

    @NotEmpty(message = "status should be not empty")
    @Pattern(regexp="^(Not Started|in Progress|Completed only)$",message="invalid status")
    private String status;

    @NotEmpty(message = "company name should be not empty")
    @Size(min = 7 , message = "company name should be more than 6 characters")
    private String companyName;



}
