package com.registration.studentregistration.controller;

import com.registration.studentregistration.dto.GenericResponse;
import com.registration.studentregistration.dto.StudentDTO;
import com.registration.studentregistration.model.Student;
import com.registration.studentregistration.service.IStudentService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService service;
    @Qualifier("studentMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Student>  getStudent () throws Exception {
       return   service.getStudent();
    }

    @PostMapping
    public ResponseEntity< GenericResponse<StudentDTO>> save(@Valid @RequestBody StudentDTO dto)  throws Exception{
        Student obj = service.save(convertToEntity(dto));
        return ResponseEntity.created(URI.create("/students/"+ obj.getIdStudent()))
                .body(new GenericResponse<>(201, "success", Arrays.asList( convertToDto(obj))) );

    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO dto, @PathVariable("id") Integer id) throws Exception{
        Student obj = service.update(convertToEntity(dto),id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private StudentDTO convertToDto(Student obj){
        return modelMapper.map(obj, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto){
        return modelMapper.map(dto, Student.class);
    }
}
