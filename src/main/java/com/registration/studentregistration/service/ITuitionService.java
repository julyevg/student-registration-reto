package com.registration.studentregistration.service;



import com.registration.studentregistration.model.Tuition;

import java.util.List;
import java.util.Map;

public interface ITuitionService extends  ICRUD<Tuition, Integer> {

    public Map<String, List<String>> relationCourseStudent();

}
