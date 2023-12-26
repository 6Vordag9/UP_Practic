package com.example.pr5_4.Repositories;

import com.example.pr5_4.Models.Class;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends CrudRepository<Class,Long> {
}
