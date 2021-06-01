package com.arritek.arittekmappingtask.services;

import com.arritek.arittekmappingtask.models.Student;
import com.arritek.arittekmappingtask.models.Subject;
import com.arritek.arittekmappingtask.models.Teacher;
import com.arritek.arittekmappingtask.repositories.StudentRepository;
import com.arritek.arittekmappingtask.repositories.SubjectRepository;
import com.arritek.arittekmappingtask.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;

    public List<Subject> findAll() {

        return subjectRepository.findAll();
    }

    public Subject save(Subject subject) {
        return  subjectRepository.save(subject);
    }

    public Subject assignStudentSubject(Long subjectId, Long studentId) {

        Subject subject = subjectRepository.findById(subjectId).get();
        Student student = studentRepository.findById(studentId).get();



        //Checking the id weather null or empty
        if (subject != null || subject.equals("") && student != null || subject.equals("")) {

            subject.enrolledStudents.add(student);
            return subjectRepository.save(subject);

        } else {
            return subject;
        }
    }
    public Subject assignTeacherSubject(Long subjectId, Long teacherId) {

        Subject subject = subjectRepository.findById(subjectId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();

        boolean hasValue = false;
        //Checking the id weather null or empty
        if (subject != null || subject.equals("") && teacher != null || subject.equals("")) {

            subject.setTeacher(teacher);
            return  subjectRepository.save(subject);

        } else {
            return subject;
        }
    }
}
