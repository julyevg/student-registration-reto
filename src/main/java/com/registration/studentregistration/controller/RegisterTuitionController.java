package com.registration.studentregistration.controller;

import com.registration.studentregistration.dto.TuitionDTO;
import com.registration.studentregistration.dto.GenericResponse;
import com.registration.studentregistration.model.Course;
import com.registration.studentregistration.model.DetailTuition;
import com.registration.studentregistration.model.Student;
import com.registration.studentregistration.model.Tuition;
import com.registration.studentregistration.repo.ICourseRepo;
import com.registration.studentregistration.repo.IDetailTuitionRepo;
import com.registration.studentregistration.repo.IStudentRepo;
import com.registration.studentregistration.service.IStudentService;
import com.registration.studentregistration.service.ITuitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterTuitionController {
    private final ITuitionService service;
    private final IStudentRepo studentRepo;
    private final ICourseRepo  courseRepo;
    @Qualifier("TuitionMapper")
    private final ModelMapper modelMapper;

    private final IDetailTuitionRepo detailTuitionRepo;
    @GetMapping
    public List<Tuition>  getTuition () throws Exception {
       return   service.readAll();
    }

    @PostMapping
    public ResponseEntity<GenericResponse<TuitionDTO>> save(@Valid @RequestBody TuitionDTO dto) throws Exception{

        Optional<Student> studentOptional = studentRepo.findById(dto.getStudent().getIdStudent());
        if (!studentOptional.isPresent()) {
            return new ResponseEntity("El estudiante no está registrado", HttpStatus.BAD_REQUEST);
        }
        Tuition tuition = convertToEntity(dto);
        tuition.setStudent(studentOptional.get());

        List <DetailTuition> detailTuitions = new ArrayList<>();

        for (DetailTuition detailDto : dto.getDetailTuition()) {
            Optional<Course> courseOptional = courseRepo.findByName(detailDto.getCourse());
            if (!courseOptional.isPresent()) {
                return new ResponseEntity("El curso '" + detailDto.getCourse() + "' no está registrado", HttpStatus.BAD_REQUEST);
            }
            DetailTuition detailTuition = new DetailTuition();
            detailTuition.setTuition(tuition);
            detailTuition.setCourse(detailDto.getCourse());
            detailTuition.setClassroom(detailDto.getClassroom());
            detailTuitions.add(detailTuition);
        }

        tuition.setDetailTuition(detailTuitions);
        service.save(tuition);
        return ResponseEntity.created(URI.create("/Tuitions/" + dto.getIdTuition()))
                .body(new GenericResponse<>(201, "success", Arrays.asList(convertToDto(tuition))));
    }




    @GetMapping("/relation-course-student")
    public ResponseEntity<Map<String, List<String>>> relationCourseStudent() {

        Map<String, List<String>> relation = service.relationCourseStudent();
        return ResponseEntity.ok(relation);
    }

    private TuitionDTO convertToDto(Tuition obj){
        return modelMapper.map(obj, TuitionDTO.class);
    }

    private Tuition convertToEntity(TuitionDTO dto){
        return modelMapper.map(dto, Tuition.class);
    }
}
