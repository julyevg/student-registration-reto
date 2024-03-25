package com.registration.studentregistration.service.impl;


import com.registration.studentregistration.model.Student;
import com.registration.studentregistration.repo.IGenericRepo;
import com.registration.studentregistration.repo.IStudentRepo;
import com.registration.studentregistration.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends  CRUDImpl<Student, Integer> implements IStudentService {


    private final IStudentRepo repo;

   public List<Student> getStudent(){
        return repo.findAll();
    }

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }

    @Override
    public Student readById(Integer id) throws Exception {
        return repo.getById(id);
    }
}
