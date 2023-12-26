package com.example.pr5_4.Repositories;

import com.example.pr5_4.Models.Schedule;
import com.example.pr5_4.Models.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject,Long> {
    @Query(value="select * from Subject where course_id=:c", nativeQuery=true)
    Subject findSubjectByCourseId (@Param("c")Long couseId);
}
