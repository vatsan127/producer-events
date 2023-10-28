package com.producer.controller;

import com.producer.database.EmployeeService;
import com.producer.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmp() {

        Employee employee = new Employee();
        employee.setEmpName("Steve");
        employee.setEmpId(1);

        when(service.saveEmp(any(Employee.class))).thenReturn(employee);

        var responseEntity = controller.createEmp(employee);

        // Assertions
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI expectedLocation = URI.create("/employees/1");
        assertThat(responseEntity.getHeaders().getLocation()).isEqualTo(expectedLocation);

        /*assertThat(responseEntity.getHeaders().getLocation().toString()).endsWith("/1");*/

        assertThat(responseEntity.getBody()).isEqualTo(employee);

        verify(service, times(1)).saveEmp(eq(employee));
    }
}
