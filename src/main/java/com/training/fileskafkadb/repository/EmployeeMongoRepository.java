package com.training.fileskafkadb.repository;

import com.training.fileskafkadb.document.EmployeeDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMongoRepository extends MongoRepository<EmployeeDoc,String> {
}
