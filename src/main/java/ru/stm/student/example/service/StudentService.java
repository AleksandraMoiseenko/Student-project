package ru.stm.student.example.service;

import java.util.List;
import org.jooq.Condition;
import ru.stm.student.example.entity.Student;

public interface StudentService {

    Student save (Student student);

    Student update(Student s);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findAll(Condition condition);

    Boolean delete(Integer id);

}
