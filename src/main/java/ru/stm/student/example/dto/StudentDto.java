package ru.stm.student.example.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stm.student.example.entity.Student;

@Data
@NoArgsConstructor
public class StudentDto implements Serializable {

    private Integer id;
    private String name;

    public StudentDto (Student student) {
        this.id = student.getId();
        this.name = student.getName();
    }

}
