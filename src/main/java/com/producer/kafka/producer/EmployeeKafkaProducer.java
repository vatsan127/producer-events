package com.producer.kafka.producer;

import com.producer.model.Employee;
import com.producer.spring.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeKafkaProducer {
    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    @Autowired
    private EmployeeServiceInterface service;
    @Value("${kafka.topic.name}")
    private String topicName;

    public void sendMessage(int id) {
        Employee employee = service.getEmployee(id);
        kafkaTemplate.send(topicName, employee);
    }

}
