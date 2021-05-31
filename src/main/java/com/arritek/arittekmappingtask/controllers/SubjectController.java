package com.arritek.arittekmappingtask.controllers;

import com.arritek.arittekmappingtask.models.Student;
import com.arritek.arittekmappingtask.models.Subject;
import com.arritek.arittekmappingtask.models.Teacher;
import com.arritek.arittekmappingtask.repositories.StudentRepository;
import com.arritek.arittekmappingtask.repositories.SubjectRepository;
import com.arritek.arittekmappingtask.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/subjects")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    Subject createSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @PutMapping(value = "/{subjectId}/student/{studentId}")
    Subject addStudentToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ) {
        Subject subject = subjectRepository.findById(subjectId).get();
        Student student = studentRepository.findById(studentId).get();
        subject.enrolledStudents.add(student);
        return subjectRepository.save(subject);
    }

    @PutMapping(value = "/{subjectId}/teacher/{teacherId}")
    String assignTeacherToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId
    ) {
        Subject subject = subjectRepository.findById(subjectId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();

        boolean hasValue = false;
        //Checking the id weather null or empty
        if (subject != null || subject.equals("") && teacher != null || subject.equals("")) {

            subject.setTeacher(teacher);
            return  ""+subjectRepository.save(subject);

        } else {
            return ""+subject;
        }
    }
}
