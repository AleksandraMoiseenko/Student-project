package ru.stm.student.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.stm.student.example.dto.StudentDto;

@Data
@AllArgsConstructor
public class Student {

    private Integer id;
    private String name;

    public Student (StudentDto student) {
        this.id = student.getId();
        this.name = student.getName();
    }

}
