package com.example.T2507e.repository;

import com.example.T2507e.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
// viết như này nó sẽ co thêm, sửa, xoá, như interface của DAO ở javacore kp viết như nó
