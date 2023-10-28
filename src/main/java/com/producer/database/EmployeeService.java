package com.producer.database;

import com.producer.model.Employee;
import com.producer.spring.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class EmployeeService implements EmployeeServiceInterface {
    @Autowired
    private EmployeeRepositry repositry;

    public Employee saveEmp(Employee employee) {
        return repositry.insert(employee);

    }

    public Employee getEmployee(int id) {
        return repositry.findById(id);
    }

    public List<Employee> getEmployees() {
        return repositry.findAll();
    }

    public Employee removeEmployee(int id) {
        if (repositry.findById(id) == null) {
            return null;
        }
        return repositry.delete(id);
    }

}
