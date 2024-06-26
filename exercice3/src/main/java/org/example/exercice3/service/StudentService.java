package org.example.exercice3.service;

import org.example.exercice3.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private final Map<UUID, Student> students;

    public StudentService() {
        students = new HashMap<>();

        Student student1 = Student.builder()
                .id(UUID.randomUUID())
                .lastname("GUEYE")
                .firstname("Youssoupha")
                .age(54)
                .email("youssouphagueye@gmail.com")
                .build();

        Student student2 = Student.builder()
                .id(UUID.randomUUID())
                .lastname("DOUMBOUYA")
                .firstname("Babacar")
                .age(21)
                .email("babacardoumbouya@gmail.com")
                .build();

        Student student3 = Student.builder()
                .id(UUID.randomUUID())
                .lastname("SECK")
                .firstname("Fatou")
                .age(20)
                .email("fatouseck@yahoo.fr")
                .build();

        students.put(student1.getId(), student1);
        students.put(student2.getId(), student2);
        students.put(student3.getId(), student3);
    }


    public Boolean addStudent(String lastname, String firstname, Integer age, String email) {
        if (lastname != null && firstname != null && age != null && email != null){
            Student student = Student.builder()
                    .id(UUID.randomUUID())
                    .lastname(lastname)
                    .firstname(firstname)
                    .age(age)
                    .email(email)
                    .build();
            students.put(student.getId(), student);
            return true;
        } else {
            return false;
        }
    }

    public Student getStudentById(UUID id) {
        return students.get(id);
    }

    public List<Student> getAllStudents(){
        return students.values().stream().toList();
    }

    //public Student getStudentByName(String name) {
       // return students.values().stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    //}

    public List<Student> getStudentByLastnameAndFirstname(String lastname,String firstname) {
        return students.values().stream()
                .filter(student -> student.getLastname().equalsIgnoreCase(lastname) && student.getFirstname().equals(firstname))
                .toList();
    }

    public List<Student> searchStudent(String name) {
        return students.values().stream()
                .filter(student -> student.getLastname().toLowerCase().startsWith(name.toLowerCase()) || student.getFirstname().toLowerCase().startsWith(name.toLowerCase()))
                .toList();
    }

    public Boolean updateStudent(UUID id, String lastname, String firstname, Integer age, String email) {
        if(getStudentById(id) != null){
            Student studentUpdate = Student.builder()
                    .id(id)
                    .lastname(lastname)
                    .firstname(firstname)
                    .email(email)
                    .age(age)
                    .build();
            students.replace(id, getStudentById(id), studentUpdate);
            return true;
        } else {
            return false;
        }
    }

    public Boolean deleteStudent(UUID id) {
        if (getStudentById(id) != null){
            students.remove(id);
            return true;
        } else {
            return false;
        }
    }


    public Object searchStudents(String search) {
        return null;
    }


}
