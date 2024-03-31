package com.registration.studentregistration.controller;

import com.registration.studentregistration.dto.CourseDTO;
import com.registration.studentregistration.dto.GenericResponse;
import com.registration.studentregistration.model.Course;
import com.registration.studentregistration.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService service;
    @Qualifier("courseMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Course>  getCourse () throws Exception {
       return   service.readAll();
    }

    @PostMapping
    public ResponseEntity< GenericResponse<CourseDTO>> save(@Valid @RequestBody CourseDTO dto)  throws Exception{

            Course obj = service.save(convertToEntity(dto));
            return ResponseEntity.created(URI.create("/courses/" + obj.getIdCourse()))
                    .body(new GenericResponse<>(201, "success", Arrays.asList(convertToDto(obj))));


    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO dto, @PathVariable("id") Integer id) throws Exception{
        Course obj = service.update(convertToEntity(dto),id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CourseDTO convertToDto(Course obj){
        return modelMapper.map(obj, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO dto){
        return modelMapper.map(dto, Course.class);
    }
}
