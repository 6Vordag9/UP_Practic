package com.example.pr5_4.Repositories;

import com.example.pr5_4.Models.Grade;
import com.example.pr5_4.Models.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule,Long> {
    @Query(value="select * from Schedule where course_id=:c", nativeQuery=true)
    Schedule findScheduleByCourseId (@Param("c")Long couseId);
}