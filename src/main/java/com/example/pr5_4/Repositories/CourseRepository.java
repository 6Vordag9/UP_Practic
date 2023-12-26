package com.example.pr5_4.Repositories;

import com.example.pr5_4.Models.Course;
import com.example.pr5_4.Models.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {

}
