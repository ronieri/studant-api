package br.com.ronieri.studentapi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email already used");
        }
        studentRepository.save(student);

        return student;
    }

    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalStateException("Student does not exist");
        }

        studentRepository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Student not found")
        );

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            if(studentRepository.findStudentByEmail(email).isPresent()){
                throw new IllegalStateException("Email already used");
            }
            student.setEmail(email);
        }

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        return student;
    }
}
