package org.example.exercice3.controller;

import org.example.exercice3.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

   /* @GetMapping("/")
    public String home(Model model){
        model.addAttribute("students",  studentService.getAllStudents());
        model.addAttribute("student", );
        return "home";
    }*/

    @GetMapping("/")
    public String homePage(){
        return "Home";
    }
}
