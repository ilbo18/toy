package com.example.userservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;

    @Builder
    public User(String name, String phoneNumber, LocalDateTime createdTime, LocalDateTime modifiedTime) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }


}
