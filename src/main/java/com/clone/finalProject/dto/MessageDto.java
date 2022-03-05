package com.clone.finalProject.dto;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class MessageDto {

    private String username;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;

}

