package com.example.event.Model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;


@AllArgsConstructor
@Data

public class Event {

    @NotEmpty(message = "Id should be not empty")
    @Size(min = 3 , message = "Id should be more than 2 characters")
    private String id ;

    @NotEmpty(message = "Description should be not empty")
    @Size(min = 16 , message = "Description should be more then 15 characters")
    private String description ;

    @NotNull(message = "Capacity should be not empty")
    @Min(value = 26 , message = "the capacity should be more than 25")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int capacity ;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate ;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate ;


}
