package com.producer.database;

import com.producer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositry {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String SELECT_QUERY = """
            select * from employee
            where emp_id = ?
            """;
    private String SELECT_ALL_QUERY = """
            select * from employee
            """;

    private String INSERT_QUERY = """
            insert into employee(emp_name ,emp_unit,location)
            values (?,?,?);        
            """;

    private String DELETE_QUERY = """
            delete from employee 
            where emp_id = ? 
            """;

    public Employee insert(Employee employee) {
        jdbcTemplate.update(INSERT_QUERY, employee.getEmpName(),
                employee.getEmpUnit(), employee.getLocation());

        String GET_EMP_ID = "SELECT MAX(emp_id) FROM employee";
        int empId = jdbcTemplate.queryForObject(GET_EMP_ID, Integer.class);

        return findById(empId);
    }

    public Employee delete(int id) {
        Employee employee = findById(id);
        jdbcTemplate.update(DELETE_QUERY, id);
        return employee;
    }

    public Employee findById(int id) {
        List<Employee> employee = jdbcTemplate.query(SELECT_QUERY,
                new BeanPropertyRowMapper<>(Employee.class), id);
        if (employee.isEmpty()) {
            return null;
        }
        return employee.get(0);

    }

    public List<Employee> findAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(Employee.class));
    }

}
