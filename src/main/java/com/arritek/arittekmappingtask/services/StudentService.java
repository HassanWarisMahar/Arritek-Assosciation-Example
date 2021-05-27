package com.arritek.arittekmappingtask.services;

import com.arritek.arittekmappingtask.exceptions.BadResourceException;
import com.arritek.arittekmappingtask.exceptions.ResourceAlreadyExistsException;
import com.arritek.arittekmappingtask.exceptions.ResourceNotFoundException;
import com.arritek.arittekmappingtask.models.Student;
import com.arritek.arittekmappingtask.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
            throw new ResourceNotFoundException("Cannot find Course with id: " + id);
        } else return student;
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
//        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
//                Sort.by("id").ascending());
        studentRepository.findAll();
        return students;
    }

    public Student save(Student student ) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(student.getName())) {
            if (student.getId() != null && existsById(student.getId())) {
                throw new ResourceAlreadyExistsException("Course with id: " +student.getId() +
                        " already exists");
            }
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
                throw new ResourceNotFoundException("Cannot find Course with id: " + student.getId());
            }
            studentRepository.save(student);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save Course");
            exc.addErrorMessage("Course is null or empty");
            throw exc;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find Course with id: " + id);
        } else {
            studentRepository.deleteById(id);
        }
    }

    public Long count() {
        return studentRepository.count();
    }
}
