package com.arritek.arittekmappingtask.controllers;

import com.arritek.arittekmappingtask.models.Subject;
import com.arritek.arittekmappingtask.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping
    List<Subject> getSubjects() {
        return subjectService.findAll();
    }

    @PostMapping
    Subject createSubject(@RequestBody Subject subject) {
        return subjectService.save(subject);
    }

    @PutMapping(value = "/{subjectId}/student/{studentId}")
    Subject addStudentToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ) {
        return subjectService.assignStudentSubject(subjectId,studentId);
    }


    @PutMapping(value = "/{subjectId}/teacher/{teacherId}")
    Subject assignTeacherToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId
    ) {
        return subjectService.assignTeacherSubject(subjectId,teacherId);
    }


}
