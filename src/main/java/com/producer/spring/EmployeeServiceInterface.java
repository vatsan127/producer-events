package com.producer.spring;

import com.producer.model.Employee;

import java.util.List;

public interface EmployeeServiceInterface {
    public Employee saveEmp(Employee employee);

    public Employee getEmployee(int id);

    public List<Employee> getEmployees();

    public Employee removeEmployee(int id);
}
