package com.producer.spring.jpa;

import com.producer.model.Employee;
import com.producer.spring.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepositry repositry;

    public Employee saveEmp(Employee employee) {
        repositry.save(employee);
        return employee;
    }

    public List<Employee> getEmployees() {
        List<Employee> employeeList = repositry.findAll();
        return employeeList;
    }

    public Employee getEmployee(int id) {
        Employee employee = repositry.findById(id).orElse(null);
        return employee;
    }

    public Employee removeEmployee(int id) {
        Employee employee = getEmployee(id);
        repositry.deleteById(id);
        return employee;
    }

}
