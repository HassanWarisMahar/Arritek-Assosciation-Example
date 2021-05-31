package com.arritek.arittekmappingtask.controllers;

import com.arritek.arittekmappingtask.exceptions.BadResourceException;
import com.arritek.arittekmappingtask.exceptions.ResourceAlreadyExistsException;
import com.arritek.arittekmappingtask.models.Teacher;
import com.arritek.arittekmappingtask.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping
    List<Teacher> getTeachers() {
        return teacherService.findAll();
    }

    @PostMapping
    Teacher createTeacher(@RequestBody Teacher teacher) throws BadResourceException, ResourceAlreadyExistsException {
        return teacherService.save(teacher);
    }
}
