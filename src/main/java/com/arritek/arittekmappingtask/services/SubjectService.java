package com.arritek.arittekmappingtask.services;

import com.arritek.arittekmappingtask.exceptions.BadResourceException;
import com.arritek.arittekmappingtask.exceptions.ResourceAlreadyExistsException;
import com.arritek.arittekmappingtask.exceptions.ResourceNotFoundException;
import com.arritek.arittekmappingtask.models.Student;
import com.arritek.arittekmappingtask.models.Subject;
import com.arritek.arittekmappingtask.models.Teacher;
import com.arritek.arittekmappingtask.repositories.StudentRepository;
import com.arritek.arittekmappingtask.repositories.SubjectRepository;
import com.arritek.arittekmappingtask.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    private boolean existsById(Long id) {
        return subjectRepository.existsById(id);
    }

    public List<Subject> findAll() {

        return subjectRepository.findAll();
    }

    //    public Subject save(Subject subject) {
//        return  subjectRepository.save(subject);
//    }
    public Subject save(Subject subject) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(subject.getName())) {
            if (subject.getId() != null && existsById(subject.getId())) {
                throw new ResourceAlreadyExistsException("Subject with id: " + subject.getId() +
                        " already exists");
            } else
                return subjectRepository.save(subject);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save Student");
            exc.addErrorMessage("Subject is null or empty");
            throw exc;
        }

    }

    public String update(Subject subject)
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(subject.getName())) {
            if (!existsById(subject.getId())) {
                //throw new ResourceNotFoundException("Cannot find Student with id: " + student.getId());
                return "Cannot find subject with id: " + subject.getId();
            }
            subjectRepository.save(subject);
        } else {
            BadResourceException exc = new BadResourceException("Failed to update subject");
            exc.addErrorMessage("Subject is null or empty");
            throw exc;
        }
        return " Subject updated with id: " + subject.getId();
    }

    public String deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            //throw new ResourceNotFoundException("Cannot find student with id: " + id);
            return "Cannot find subject with id: " + id;
        } else {
            subjectRepository.deleteById(id);
            return " deleted successfully!";
        }
    }

    public Long count() {
        return subjectRepository.count();
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
            return subjectRepository.save(subject);

        } else {
            return subject;
        }
    }
}
