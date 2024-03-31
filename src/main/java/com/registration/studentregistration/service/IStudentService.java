package com.registration.studentregistration.service;


import com.registration.studentregistration.model.Student;

import java.util.List;

public interface IStudentService  extends  ICRUD<Student, Integer> {

    List<Student> getStudent();

}
