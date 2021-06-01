package com.arritek.arittekmappingtask.repositories;

import com.arritek.arittekmappingtask.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
