package com.darmokhval.springtest.repository;

import com.darmokhval.springtest.entity.Car;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMongoRepository extends MongoRepository<Car, ObjectId> {

}
