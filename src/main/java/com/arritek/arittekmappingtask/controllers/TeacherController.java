package com.arritek.arittekmappingtask.controllers;

import com.arritek.arittekmappingtask.exceptions.BadResourceException;
import com.arritek.arittekmappingtask.exceptions.ResourceAlreadyExistsException;
import com.arritek.arittekmappingtask.exceptions.ResourceNotFoundException;
import com.arritek.arittekmappingtask.models.Teacher;
import com.arritek.arittekmappingtask.services.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    @PutMapping
    Teacher updateTeacher(@RequestBody Teacher teacher) throws BadResourceException, ResourceAlreadyExistsException, ResourceNotFoundException {
        return teacherService.update(teacher);
    }
    @DeleteMapping(value = "/{id}")
    public String deleteTeacherById(@PathVariable long id )throws ResourceNotFoundException {
        try {
           return teacherService.deleteById(id);

        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            return teacherService.deleteById(id);
        }
    }

}
