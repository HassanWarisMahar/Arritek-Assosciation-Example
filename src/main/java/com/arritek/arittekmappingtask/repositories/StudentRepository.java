package com.arritek.arittekmappingtask.repositories;

import com.arritek.arittekmappingtask.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContaining(String name);

    Optional<Student> findById(Long studentId);
}
