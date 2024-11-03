package com.example.To_Do.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String username;
    private String email;
    private String password;

}
