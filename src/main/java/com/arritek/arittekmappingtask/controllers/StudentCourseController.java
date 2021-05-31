//package com.arritek.arittekmappingtask.controllers;
//
//import com.arritek.arittekmappingtask.exceptions.BadResourceException;
//import com.arritek.arittekmappingtask.exceptions.ResourceAlreadyExistsException;
//import com.arritek.arittekmappingtask.exceptions.ResourceNotFoundException;
//import com.arritek.arittekmappingtask.models.Student;
//import com.arritek.arittekmappingtask.models.Subject;
//import com.arritek.arittekmappingtask.services.StudentService;
//import com.arritek.arittekmappingtask.services.SubjectService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class StudentCourseController {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//   // private final int ROW_PER_PAGE = 5;
//
//    @Autowired
//    private StudentService studentService;
//    @Autowired
//    private SubjectService subjectService;
//
//    @GetMapping("/student")
//    public void assignCourse() throws BadResourceException, ResourceAlreadyExistsException {
//        Student student = new Student("Hassan Waris", 15, "A", null);
//        studentService.save(student);
//    }
//
////    // create a student
////    Student student = new Student("John Doe", 15, "8th");
////
////    // save the student
////            studentRepository.save(student);
////
////    // create three courses
////    Course course1 = new Course("Machine Learning", "ML", 12, 1500);
////    Course course2 = new Course("Database Systems", "DS", 8, 800);
////    Course course3 = new Course("Web Basics", "WB", 10, 0);
////    // save courses
////            courseRepository.saveAll(Arrays.asList(course1, course2, course3));
////    // add courses to the student
////            student.getCourses().addAll(Arrays.asList(course1, course2, course3));
////    // update the student
////            studentRepository.save(student);
////
//
//    //****************************************************************************
//    @GetMapping(value = "/courses")
//    public String getCourses(Model model) {
//        Subject subject = null;
//        try {
//
//            List<Subject> courses =subjectService.findAll();
//            model.addAttribute("subjectList", courses);
//
//        } catch (Exception ex) {
//            model.addAttribute("errorMessage", "Course not found");
//        }
//        return "course";
//    }
//
//
//    @GetMapping(value = "/courses/{courseID}")
//    public String getCourseById(Model model, @PathVariable long courseId) {
//        Subject subject = null;
//        try {
//
//            subject = (Subject) subjectService.findById(courseId);
//            model.addAttribute("subject", subject);
//
//        } catch (Exception ex) {
//            model.addAttribute("errorMessage", "Course not found");
//        }
//        return "course";
//    }
//
//    @GetMapping(value = {"/courses/add"})
//    public String showAddCourse(Model model) {
//        Subject subject = new Subject();
//        model.addAttribute("add", true);
//        model.addAttribute("course", subject);
//
//        return "course-edit";
//    }
//
//    @PostMapping(value = "/courses/add")
//    public String addCourse(Model model,
//                             @ModelAttribute("course") Subject subject) {
//        try {
//            Subject newCourse = subjectService.save(subject);
//            return "redirect:/subjects/" + String.valueOf(newCourse.getId());
//
//        } catch (Exception ex) {
//            //log exception first ,then show error
//            String errorMessage = ex.getMessage();
//            logger.error(errorMessage);
//
//            //model.addAttribute("course", course)
//            model.addAttribute("add", true);
//
//        }
//
//        return "course-edit";
//    }
//
//    @GetMapping(value = {"/courses/{courseId}/edit"})
//    public String showEditCourse(Model model, @PathVariable long courseId) {
//        Course course = null;
//        try {
//            course = courseService.findById(courseId);
//        } catch (ResourceNotFoundException ex) {
//            model.addAttribute("errorMessage", "Course not found");
//        }
//        model.addAttribute("add", false);
//        model.addAttribute("course", course);
//        return "course-edit";
//    }
//
//    @PostMapping(value = {"/courses/{courseId}/edit"})
//    public String updateCourse(Model model,
//                                @PathVariable long courseId,
//                                @ModelAttribute("course") Course course) {
//        try {
//            course.setId(courseId);
//            courseService.update(course);
//            return "redirect:/courses/" + String.valueOf(course.getId());
//        } catch (Exception ex) {
//            // log exception first,
//            // then show error
//            String errorMessage = ex.getMessage();
//            logger.error(errorMessage);
//            model.addAttribute("errorMessage", errorMessage);
//
//            model.addAttribute("add", false);
//            return "course-edit";
//        }
//    }
//
//    @GetMapping(value = {"/courses/{courseId}/delete"})
//    public String showDeleteCourseById(
//            Model model, @PathVariable long courseId) {
//        Course course = null;
//        try {
//            course = courseService.findById(courseId);
//
//            model.addAttribute("allowDelete", true);
//            model.addAttribute("course", course);
//        } catch (ResourceNotFoundException ex) {
//            model.addAttribute("errorMessage", "Course not found ");
//        }
//        return "course";
//    }
//
//    @PostMapping(value = {"/courses/{courseId}/delete"})
//    public String deleteCourseById(
//            Model model, @PathVariable long courseId) {
//        try {
//            subjectService.deleteById(courseId);
//            return "redirect:/courses";
//        } catch (ResourceNotFoundException ex) {
//            String errorMessage = ex.getMessage();
//            logger.error(errorMessage);
//            model.addAttribute("errorMessage", errorMessage);
//            return "course";
//        }
//    }
//}
