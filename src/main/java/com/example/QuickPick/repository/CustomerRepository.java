package com.example.QuickPick.repository;

import com.example.QuickPick.enums.Gender;
import com.example.QuickPick.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "select c from Customer c where c.gender=:gender and c.age>=:age")
    public List<Customer> getCustomerByGenderAndAgeGreaterThan(Gender gender, int age);

  //  @Query(value = "select c from Customer c where c.email=:email")
    public Customer findByEmail(String email);
}
