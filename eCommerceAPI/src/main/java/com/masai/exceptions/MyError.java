package com.masai.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class MyError {

    private LocalDate localDate;
    private String message;
    private String description;

}
