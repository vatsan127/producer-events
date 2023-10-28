package com.producer.controller;

import com.producer.exception.EmployeeNotFoundException;
import com.producer.kafka.producer.EmployeeKafkaProducer;
import com.producer.model.Employee;
import com.producer.spring.EmployeeServiceInterface;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@NoArgsConstructor
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceInterface service;
    @Autowired
    private EmployeeKafkaProducer employeeKafkaProducer;

    @GetMapping("/project/emp/{id}")
    public ResponseEntity<Object> retrieveEmployee(@PathVariable int id) {

        Employee employee = service.getEmployee(id);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/project/emp")
    public List<Employee> retrieveAllEmployees() {
        return service.getEmployees();
    }


    @PostMapping("/project/emp")
    public ResponseEntity createEmp(@Valid @RequestBody Employee employee) {
        Employee savedEmp = service.saveEmp(employee);
        //employeeKafkaProducer.sendMessage(savedEmp.getEmpId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/{id}")
                .buildAndExpand(savedEmp.getEmpId()).toUri();
        return ResponseEntity.created(location).body(savedEmp);
    }

    @DeleteMapping("/project/emp/{id}")
    public ResponseEntity deleteEmp(@PathVariable int id) {
        Employee employee = service.removeEmployee(id);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return ResponseEntity.ok("Employee Removed successfully");

    }
}
