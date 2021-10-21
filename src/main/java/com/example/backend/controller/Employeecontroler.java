package com.example.backend.controller;

import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.model.Employee;
import com.example.backend.repo.Employeerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/emp/")
public class Employeecontroler
{
    @Autowired
    private final Employeerepo emprepo;

    public Employeecontroler(Employeerepo emprepo) {
        this.emprepo = emprepo;
    }

    //get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees()
    {
     return emprepo.findAll();
    }

    // create employee rest api
    @PostMapping("/employees")
    public Employee addemployee(@RequestBody Employee emp)
    {
        return emprepo.save(emp);
    }
    //get Employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  Long id)
    {
        Employee emp= emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee does not exsist with"+ id ));
        return ResponseEntity.ok(emp);
    }

    //update Employee
    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateemployee(@PathVariable Long id,@RequestBody Employee empdetails)
    {
      Employee emp=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee does not exsist with"+ id));
      emp.setName(empdetails.getName());
      emp.setEmail(empdetails.getEmail());
      emp.setJobTitle(empdetails.getJobTitle());

      Employee update=emprepo.save(emp);
      return ResponseEntity.ok(update);
    }

    //delete Employee
    @DeleteMapping("employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id)
    {
        Employee emp=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee does not exsist with"+ id));
        emprepo.delete(emp);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
