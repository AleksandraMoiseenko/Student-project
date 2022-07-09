package ru.stm.student.example.repository;

import static ru.stm.student.example.entity.tables.Student.STUDENT;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.exception.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.stm.student.example.entity.Student;
import ru.stm.student.example.entity.tables.records.StudentRecord;

@Repository
@RequiredArgsConstructor
public class StudentRepository implements CrudRepository<Student>{
    private final DSLContext dsl;

    @Override
    public Student insert(Student student) {
        return dsl.insertInto(STUDENT, STUDENT.NAME)
            .values(student.getName())
            .returningResult(STUDENT.ID, STUDENT.NAME).fetchOne().into(Student.class);
    }

    @Override
    public Student update(Student student) {
        return dsl.update(STUDENT)
            .set(dsl.newRecord(STUDENT, student))
            .where(STUDENT.ID.eq(student.getId()))
            .returningResult(STUDENT.ID, STUDENT.NAME).fetchOne().into(Student.class);
    }

    @Override
    public Student find(Integer id) {
        StudentRecord studentRecord = dsl.fetchSingle(STUDENT, STUDENT.ID.eq(id));
        return new Student(studentRecord.getId(), studentRecord.getName());
    }

    @Override
    public List<Student> findAll(Condition condition) {
        return dsl.selectFrom(STUDENT)
            .where(condition)
            .fetch()
            .map(r -> r.into(Student.class));
    }

    @Override
    public List<Student> findAll() {
        return dsl.selectFrom(STUDENT)
            .fetch()
            .map(r -> r.into(Student.class));
    }

    @Override
    public Boolean delete(Integer id) {
        return dsl.deleteFrom(STUDENT)
            .where(STUDENT.ID.eq(id))
            .execute() == 1;
    }
}
