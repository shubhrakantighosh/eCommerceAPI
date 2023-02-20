package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class UserSession {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO )
    private Integer uuid;
    @ManyToOne(cascade = CascadeType.ALL )
    private User user;
    private LocalDateTime start;
    private LocalDateTime end;

}
