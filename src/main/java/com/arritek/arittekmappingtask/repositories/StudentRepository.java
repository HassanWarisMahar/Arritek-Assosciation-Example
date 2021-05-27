package com.arritek.arittekmappingtask.repositories;

import com.arritek.arittekmappingtask.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByNameContaining(String name);
}
