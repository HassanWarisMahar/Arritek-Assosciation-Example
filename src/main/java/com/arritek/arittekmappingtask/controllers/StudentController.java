package com.arritek.arittekmappingtask.controllers;

import com.arritek.arittekmappingtask.exceptions.BadResourceException;
import com.arritek.arittekmappingtask.exceptions.ResourceAlreadyExistsException;
import com.arritek.arittekmappingtask.exceptions.ResourceNotFoundException;
import com.arritek.arittekmappingtask.models.Student;
import com.arritek.arittekmappingtask.repositories.StudentRepository;
import com.arritek.arittekmappingtask.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    List<Student> getStudents() {
        return (List<Student>) studentService.findAll();
    }

    @PostMapping()
    Student createStudent(@RequestBody Student student) throws BadResourceException, ResourceAlreadyExistsException {
         studentService.save(student);
         return (Student) studentService.findAll();
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") Long studentId) throws ResourceNotFoundException {
        String message = studentService.deleteById(studentId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
