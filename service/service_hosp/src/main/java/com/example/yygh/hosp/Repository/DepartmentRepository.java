package com.example.yygh.hosp.Repository;

import com.exmaple.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {


    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);

}