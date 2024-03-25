package com.registration.studentregistration.config;

import com.registration.studentregistration.dto.StudentDTO;
import com.registration.studentregistration.model.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    /*@Bean("studentMapper")
    public ModelMapper studentMapper(){
        ModelMapper mapper = new ModelMapper();

        TypeMap<Student, StudentDTO> typeMap1 = mapper.createTypeMap(Student.class, StudentDTO.class);
        typeMap1.addMapping(Student::getName, (dest, v)-> dest.setName((String) v));
        return  mapper;
    }*/
    @Bean("defaultMapper")
    public ModelMapper defaultMapper(){
        return new ModelMapper();
    }
}
