package ru.stm.student.example.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stm.student.example.datacache.CacheStore;
import ru.stm.student.example.entity.Student;
import ru.stm.student.example.repository.StudentRepository;
import ru.stm.student.example.service.StudentService;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CacheStore<Student> cache;

    @Override
    public Student save(Student student) {
        Student result = studentRepository.insert(student);
        cache.add(result.getId(), result);
        return result;
    }

    @Override
    public Student update(Student s) {
        return studentRepository.update(s);
    }

    @Override
    public Student findById(Integer id) {
        Student result = cache.get(id);
        if (result != null) {
            return result;
        }
        result = studentRepository.find(id);
        cache.add(result.getId(), result);
        return result;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAll(Condition condition) {
        return studentRepository.findAll(condition);
    }

    @Override
    public Boolean delete(Integer id) {
        return studentRepository.delete(id);
    }
}
