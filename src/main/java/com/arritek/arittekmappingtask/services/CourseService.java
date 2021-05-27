package com.arritek.arittekmappingtask.services;

import com.arritek.arittekmappingtask.exceptions.BadResourceException;
import com.arritek.arittekmappingtask.exceptions.ResourceAlreadyExistsException;
import com.arritek.arittekmappingtask.exceptions.ResourceNotFoundException;
import com.arritek.arittekmappingtask.models.Course;
import com.arritek.arittekmappingtask.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    private boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    public Course findById(Long id) throws ResourceNotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new ResourceNotFoundException("Cannot find Course with id: " + id);
        } else return course;
    }

    public List<Course> findAll() {
        List<Course> Courses = new ArrayList<>();
//        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
//                Sort.by("id").ascending());
        courseRepository.findAll().forEach(Courses::add);
        return Courses;
    }

    public Course save(Course course ) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(course.getTitle())) {
            if (course.getId() != null && existsById(course.getId())) {
                throw new ResourceAlreadyExistsException("Course with id: " + course.getId() +
                        " already exists");
            }
            return courseRepository.save(course);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save Course");
            exc.addErrorMessage("Course is null or empty");
            throw exc;
        }
    }

    public void update(Course course)
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(course.getTitle())) {
            if (!existsById(course.getId())) {
                throw new ResourceNotFoundException("Cannot find Course with id: " + course.getId());
            }
            courseRepository.save(course);
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
            courseRepository.deleteById(id);
        }
    }

    public Long count() {
        return courseRepository.count();
    }
}
