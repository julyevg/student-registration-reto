package com.registration.studentregistration.repo;

import com.registration.studentregistration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepo  extends IGenericRepo<Student, Integer> {


}
