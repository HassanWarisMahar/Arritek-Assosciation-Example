package com.arritek.arittekmappingtask.services;

import com.arritek.arittekmappingtask.exceptions.BadResourceException;
import com.arritek.arittekmappingtask.exceptions.ResourceAlreadyExistsException;
import com.arritek.arittekmappingtask.exceptions.ResourceNotFoundException;
import com.arritek.arittekmappingtask.models.Student;
import com.arritek.arittekmappingtask.models.Teacher;
import com.arritek.arittekmappingtask.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    private boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    public Student findById(Long id) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new ResourceNotFoundException("Cannot find student with id: " + id);
        } else return student;
    }

    public List<Student> findAll() {

        return (List<Student>) studentRepository.findAll();
    }

    public Student save(Student student) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(student.getName())) {
            if (student.getId() != null && existsById(student.getId())) {
                throw new ResourceAlreadyExistsException("Student with id: " + student.getId() +
                        " already exists");
            }else
            return studentRepository.save(student);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save Student");
            exc.addErrorMessage("Student is null or empty");
            throw exc;
        }

    }

    public void update(Student student)
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(student.getName())) {
            if (!existsById(student.getId())) {
                throw new ResourceNotFoundException("Cannot find Student with id: " + student.getId());
            }
            studentRepository.save(student);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save Student");
            exc.addErrorMessage("Student is null or empty");
            throw exc;
        }
    }

    public String deleteStudent(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        //Remove the related subjects from student entity.
        if(student.isPresent()) {
            student.get().removeSubjects();
            studentRepository.deleteById(student.get().getId());
            return "Student with id: " + studentId + " deleted successfully!";
        }
        return null;
    }

    public String  deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            //throw new ResourceNotFoundException("Cannot find student with id: " + id);
           return "Cannot find student with id: " + id;
        } else {
             studentRepository.deleteById(id);
            return "Succssfull";
        }
    }

    public Long count() {
        return studentRepository.count();
    }
}
