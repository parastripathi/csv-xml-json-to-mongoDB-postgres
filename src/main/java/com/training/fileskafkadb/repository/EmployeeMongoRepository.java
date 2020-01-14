package com.training.fileskafkadb.repository;

import com.training.fileskafkadb.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMongoRepository extends MongoRepository<Employee,String> {
}
