package com.lxk.repository;

import com.lxk.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lxk on 2017/4/6
 */
@Repository("studentRepository")
public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{'id':?0}")
    Student findById(String id);

    @Query("{'name':?0}")
    List<Student> findByName(String name);

}