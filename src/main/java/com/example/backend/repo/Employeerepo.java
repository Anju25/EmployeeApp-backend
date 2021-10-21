package com.example.backend.repo;

import com.example.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Employeerepo extends JpaRepository<Employee,Long>
{

}
