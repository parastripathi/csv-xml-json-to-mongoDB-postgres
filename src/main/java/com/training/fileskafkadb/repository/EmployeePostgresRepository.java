package com.training.fileskafkadb.repository;

import com.training.fileskafkadb.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePostgresRepository extends CrudRepository<Employee,String> {
}
