package org.example.exercice3.controller;

import org.example.exercice3.model.Student;
import org.example.exercice3.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class StudentController {
    private final StudentService studentService;

    private String name;
    private String address;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        return "home";
    }

   /* @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "form/form";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute("student") Student student) {
        System.out.println(student.getLastname());
        System.out.println(student.getFirstname());
        System.out.println(student.getAge());
        System.out.println(student.getEmail());
        return "redirect:/";
    }*/

    @GetMapping("/add")
    public String addStudent(@RequestParam(value = "id", required = false) UUID id, Model model){
        if (id != null){
            model.addAttribute("student", studentService.getStudentById(id));
        } else {
            model.addAttribute("student", new Student());
        }
        return "student/add";
    }

    @PostMapping("/add")
    public String registerStudent(@Valid @ModelAttribute("student")Student student, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "student/add";
        } else {
           if (student.getId() != null) {
              studentService.updateStudent(student.getId(), student.getLastname(), student.getFirstname(), student.getAge(), student.getEmail());
                return "redirect:/students";
            } else {
                if (studentService.addStudent(student.getLastname(), student.getFirstname(), student.getAge(), student.getEmail())){
                    return "redirect:/students";
                } else {
                    return "redirect:/";
                }
            }
        }
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")UUID id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "student/add";
    }

    @GetMapping("/students")
    public String showStudents(Model model){
        List<Student> students = studentService. getStudentByLastnameAndFirstname();
        model.addAttribute("students", students);
        return "student/studentList";
    }

    @GetMapping("/search")
    public String searchStudent(@RequestParam(value = "search", required = false)String name, Model model){
        model.addAttribute("student", new Student());
        List<Student> students = studentService.searchStudent(name);

        if (!students.isEmpty()){
            model.addAttribute("students", students);
            return "student/studentList";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/student/{studentId}")
    public String showStudent(@PathVariable("studentId")UUID id, Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student/studentDetails";
    }


}
