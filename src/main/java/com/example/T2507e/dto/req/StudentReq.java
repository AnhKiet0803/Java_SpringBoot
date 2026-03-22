package com.example.T2507e.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class StudentReq {
    private Long id;

    @NotBlank(message = "Input students name")
    @Min(value = 3, message = "Input string min 3 characters")
    private String name;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @NotNull(message = "Input students mark")
    @Min(0)
    @Max(10)
    private Integer mark;

    @NotNull
    private Long group_id;
}
