package com.example.T2507e.service;

import com.example.T2507e.dto.req.StudentReq;
import com.example.T2507e.dto.res.StudentRes;
import com.example.T2507e.entity.Student;
import com.example.T2507e.repository.GroupRepository;
import com.example.T2507e.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    public List<StudentRes> getAllStudent(){
        //List<Student> rs = studentRepository.findAll();
        //List<StudentRes> data = new ArrayList<>();
        //for(Student s: rs){
        //    StudentRes sr = StudentRes.toJison(s);
        //    data.add(sr);
        //}
        //return data;

        //return studentRepository.findAll().stream().map(s->StudentRes.toJison(s)).toList();

        return studentRepository.findAll().stream().map(StudentRes::toJison).toList();

        // 3 cách này như nhau
    }

    public StudentRes findbyId(Long id){
        return StudentRes.toJison(studentRepository.findById(id).get());
    }

    public StudentRes create(StudentReq req){
        try {
            Student s = new Student();
            s.setName(req.getName());
            s.setDob(req.getDob());
            s.setMark(req.getMark());
            s.setGroup(groupRepository.findById(req.getGroup_id()).get());
            return StudentRes.toJison(studentRepository.save(s));
        }catch (Exception e){
            return null;
        }
    }

    public StudentRes update(Long id, StudentReq req){
        try {
            Student s = studentRepository.findById(id).get();
            s.setName(req.getName());
            s.setDob(req.getDob());
            s.setMark(req.getMark());
            s.setGroup(groupRepository.findById(req.getGroup_id()).get());
            s = studentRepository.save(s);
            return StudentRes.toJison(s);
        }catch (Exception e){
            return null;
        }
    }

    public void delete(Long id){
        studentRepository.deleteById(id);
    }

}
