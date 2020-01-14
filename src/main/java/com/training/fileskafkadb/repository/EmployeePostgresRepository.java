package com.training.fileskafkadb.repository;

import com.training.fileskafkadb.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePostgresRepository extends JpaRepository<Employee,String> {
}
