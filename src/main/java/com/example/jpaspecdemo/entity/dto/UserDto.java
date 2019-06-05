package com.example.jpaspecdemo.entity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {

    private Integer userId;

    private String username;

    private String roleName;

    private String addressName;
}
