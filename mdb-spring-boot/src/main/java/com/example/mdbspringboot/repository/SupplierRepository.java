package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepository extends MongoRepository<Supplier, String> {

    Supplier findByCompanyName(String companyName);
}