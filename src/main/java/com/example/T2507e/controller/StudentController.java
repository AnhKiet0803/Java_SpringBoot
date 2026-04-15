package com.example.T2507e.controller;

import com.example.T2507e.common.ResponseHandler;
import com.example.T2507e.dto.common.ResponseDTO;
import com.example.T2507e.dto.req.StudentReq;
import com.example.T2507e.dto.res.StudentRes;
import com.example.T2507e.enums.StatusCode;
import com.example.T2507e.service.StudentService;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"})
//@CrossOrigin(origins = "*")
public class StudentController {
    private StudentService studentService;

    @GetMapping()
    //public List<StudentRes> getAllStudent(){
    //    return studentService.getAllStudent();
    //}
    public ResponseEntity<ResponseDTO<List<StudentRes>>> getAllStudent(){
        try{
            return ResponseHandler.success(studentService.getAllStudent(),"Thành công");
        }catch (Exception e){
            return ResponseHandler.error(StatusCode.BAD_REQUEST,e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO<List<StudentRes>>> getAllStudents(){
        try {
            return ResponseHandler.success(studentService.getAllStudent(),"Thành công!");
        }catch (Exception e){
            return ResponseHandler.error(StatusCode.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<StudentRes>> findStudentById(@PathVariable Long id){
        try{
            return ResponseHandler.success(studentService.findbyId(id),"Thành công");
        }catch (Exception e){
            return ResponseHandler.error(StatusCode.BAD_REQUEST,e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO<StudentRes>> createStudent(@RequestBody StudentReq req){
        try{
            return ResponseHandler.success(studentService.create(req),"Thành công");
        }catch (ValidationException v){
            return ResponseHandler.error(StatusCode.VALIDATION_ERROR,v.getMessage());
        }catch (Exception e){
            return ResponseHandler.error(StatusCode.BAD_REQUEST,e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public StudentRes updateStudent(@PathVariable Long id,@RequestBody StudentReq req){
        return studentService.update(id,req);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.delete(id);
        return "xoá thành công";
    }
}
