package com.registration.studentregistration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Integer idStudent;


    private String name;


    private String lastName;

    private String dni;

    private Integer age;
}
