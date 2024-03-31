package com.registration.studentregistration.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.registration.studentregistration.model.DetailTuition;
import com.registration.studentregistration.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TuitionDTO {

    private Integer idTuition;
    private LocalDateTime dateTuition;

    //@ManyToOne
    private Student student;


    private List<DetailTuition> detailTuition;

    private Boolean state;



}
