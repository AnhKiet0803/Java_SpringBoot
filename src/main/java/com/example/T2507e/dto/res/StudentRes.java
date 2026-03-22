package com.example.T2507e.dto.res;

import com.example.T2507e.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@AllArgsConstructor
@Setter
@Getter
public class StudentRes {
    private Long id;
    private String name;
    private Date dob;

    public static StudentRes toJison(Student student){
        return new StudentRes(student.getId(),
                student.getName(),
                student.getDob()
        );
    }
}
