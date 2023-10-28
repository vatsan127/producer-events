package com.producer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    @Column
    @JsonProperty
    @Size(min = 3, message = "Name not Valid!")
    private String empName;

    @Column
    @JsonProperty
    @Size(min = 3, message = "Unit not Valid!")
    private String empUnit;

    @Column
    @JsonProperty
    @Size(min = 3, message = "Location not Valid!")
    private String location;
}
