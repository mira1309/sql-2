package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "age")
    public List<Student> findWhenAgeBetween(@RequestParam Integer min, @RequestParam Integer max) {
        return studentService.findWhenAgeBetween(min, max);
    }

    @GetMapping("/students-by-faculty-id")
    public List<Student> getStudentsByFacultyId(@RequestParam Long facultyId) {
        return studentService.findStudentsByFacultyId(facultyId);
    }

    //получить факультет по id студента
    @GetMapping("/faculty-by-student-id")
    public List<Student> getFacultyByStudentIdOrName(@RequestParam (required = false) Long id,
                                                     @RequestParam (required = false) String name) {
        return studentService.findFacultyByStudentIdOrName(id, name);
    }
}
