package com.arritek.arittekmappingtask.services;

import com.arritek.arittekmappingtask.exceptions.BadResourceException;
import com.arritek.arittekmappingtask.exceptions.ResourceAlreadyExistsException;
import com.arritek.arittekmappingtask.exceptions.ResourceNotFoundException;
import com.arritek.arittekmappingtask.models.Teacher;
import com.arritek.arittekmappingtask.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    private boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }

    public Teacher findById(Long id) throws ResourceNotFoundException {
        Teacher contact = teacherRepository.findById(id).orElse(null);
        if (contact == null) {
            throw new ResourceNotFoundException("Cannot find Teacher with id: " + id);
        } else return contact;
    }

    public Teacher save(Teacher teacher) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(teacher.getName())) {
            if (teacher.getId() != null && existsById(teacher.getId())) {
                throw new ResourceAlreadyExistsException("Contact with id: " +teacher.getId() +
                        " already exists");
            }
            return teacherRepository.save(teacher);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save contact");
            exc.addErrorMessage("Contact is null or empty");
            throw exc;
        }
    }

    public void update(Teacher teacher)
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(teacher.getName())) {
            if (!existsById(teacher.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " +teacher.getId());
            }
            teacherRepository.save(teacher);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save contact");
            exc.addErrorMessage("Contact is null or empty");
            throw exc;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find contact with id: " + id);
        } else {
            teacherRepository.deleteById(id);
        }
    }

    public Long count() {
        return teacherRepository.count();
    }

    public List<Teacher> findAll() {

       return teacherRepository.findAll();
    }
}
