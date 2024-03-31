package com.registration.studentregistration.service.impl;


import com.registration.studentregistration.model.Course;
import com.registration.studentregistration.repo.ICourseRepo;
import com.registration.studentregistration.repo.IGenericRepo;
import com.registration.studentregistration.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends  CRUDImpl<Course, Integer> implements ICourseService {


    private final ICourseRepo repo;

   public List<Course> getStudent(){
        return repo.findAll();
    }

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }

    @Override
    public Course readById(Integer id) throws Exception {
        return repo.getById(id);
    }
}
