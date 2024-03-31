package com.registration.studentregistration.service.impl;


import com.registration.studentregistration.model.Course;
import com.registration.studentregistration.model.DetailTuition;
import com.registration.studentregistration.model.Student;
import com.registration.studentregistration.model.Tuition;
import com.registration.studentregistration.repo.IGenericRepo;
import com.registration.studentregistration.repo.ITuitionRepo;
import com.registration.studentregistration.service.ITuitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TuitionServiceImpl extends  CRUDImpl<Tuition, Integer> implements ITuitionService {


    private final ITuitionRepo repo;

   public List<Tuition> getTuition(){
        return repo.findAll();
    }

    @Override
    protected IGenericRepo<Tuition, Integer> getRepo() {
        return repo;
    }

    @Override
    public Tuition readById(Integer id) throws Exception {
        return repo.getById(id);
    }

   public Map<String, List<String>> relationCourseStudent() {

       List<Tuition> tuitions = repo.findAll();
       Map<String, List<String>> relacionCursosEstudiantes = tuitions.stream()
               .flatMap(tuition -> tuition.getDetailTuition().stream())
               .collect(Collectors.groupingBy(
                       DetailTuition::getCourse,
                       Collectors.mapping(detailTuition -> detailTuition.getTuition().getStudent().getName(), Collectors.toList())));


        return relacionCursosEstudiantes;

   }
}
