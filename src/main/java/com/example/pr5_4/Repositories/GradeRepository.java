package com.example.pr5_4.Repositories;

import com.example.pr5_4.Models.Grade;
import com.example.pr5_4.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface GradeRepository extends CrudRepository<Grade,Long> {

    @Query(value="select * from Grade where user_id=:c", nativeQuery=true)
    Grade findGradesByUserId (@Param("c")Long userId);
}
