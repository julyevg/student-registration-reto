package com.registration.studentregistration.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Tuition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idTuition;

    private LocalDateTime dateTuition;
    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name = "FK_id_student"))
    private Student student;


    @OneToMany(mappedBy = "tuition", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "tuition")
   // @JoinColumn(name = "id_detail_tuition", nullable = false,foreignKey = @ForeignKey(name = "FK_id_detail_tuition"))
    private List<DetailTuition> detailTuition;

    private Boolean state;



}
