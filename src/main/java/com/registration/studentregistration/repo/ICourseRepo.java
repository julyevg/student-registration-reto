 package com.registration.studentregistration.repo;

import com.registration.studentregistration.model.Course;

import java.util.Optional;


 public interface ICourseRepo extends IGenericRepo<Course, Integer> {
    Optional<Course> findByName(String name);

}
