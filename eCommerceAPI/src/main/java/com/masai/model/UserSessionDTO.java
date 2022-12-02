package com.masai.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserSessionDTO {

    private Integer uuid;
    private String userId;
    private LocalDateTime start;
    private LocalDateTime end;
}
