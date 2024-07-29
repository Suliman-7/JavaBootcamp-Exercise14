package com.example.event.Controller;


import com.example.event.Api.ApiResponse;
import com.example.event.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class eventController {

    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/events")
    public ArrayList<Event> getEvents() {
        return events;
    }

    @PostMapping("/post")
    public ResponseEntity addEvent(@Valid @RequestBody Event event , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index,@Valid @RequestBody Event event , Errors errors ) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        if(index >= events.size()){
            return ResponseEntity.status(404).body("event not found");
        }
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("event deleted successfully !"));
    }


    @PutMapping("/changecapacity/{index}/{newcapacity}")
    public ResponseEntity changeCapacity(@PathVariable int newcapacity, @PathVariable int index) {
        if(newcapacity<=25){
            return ResponseEntity.status(400).body(new ApiResponse("capacity should be greater than 25 !"));
        }
        events.get(index).setCapacity(newcapacity);
        return ResponseEntity.status(200).body(new ApiResponse("event capacity changed successfully !"));
    }

    @GetMapping("search/{id}")
    public ResponseEntity searchProject(@PathVariable String id) {
        for(Event event : events) {
            if(event.getId().equals(id)) {
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(404).body(new ApiResponse("event not found"));
    }






}
