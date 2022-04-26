package com.example.userservice.dto;

import com.example.userservice.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @NotEmpty(message = "INVALID_NAME")
    private String name;

    @NotEmpty(message = "INVALID_PHONE_NUMBER")
    private String phoneNumber;



    public User toEntity() {
        return User.builder()
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .createdTime(LocalDateTime.now())
                .modifiedTime(LocalDateTime.now())
                .build();

    }
}
