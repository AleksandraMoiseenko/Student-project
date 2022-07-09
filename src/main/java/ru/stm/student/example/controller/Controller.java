package ru.stm.student.example.controller;

import static ru.stm.student.example.entity.tables.Student.STUDENT;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stm.student.example.dto.StudentDto;
import ru.stm.student.example.entity.Student;
import ru.stm.student.example.service.StudentService;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class Controller {

    private final StudentService studentService;

    @PostMapping
    public StudentDto save(@RequestBody StudentDto student) {
        Student result = studentService.save(new Student(student));
        return new StudentDto(result);
    }

    @PutMapping
    public StudentDto update(@RequestBody StudentDto student) {
        return new StudentDto(studentService.update(new Student(student)));
    }

    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll().stream().map(StudentDto::new).collect(Collectors.toList());
    }

    @GetMapping("/name/{name}")
    public List<StudentDto> findByName(@PathVariable String name) {
        return studentService.findAll(STUDENT.NAME.eq(name)).stream().map(StudentDto::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable Integer id) {
        return new StudentDto(studentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public Boolean delete (@PathVariable Integer id) {
        return studentService.delete(id);
    }

}
