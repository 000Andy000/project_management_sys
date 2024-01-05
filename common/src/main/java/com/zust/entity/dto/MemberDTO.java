package com.zust.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MemberDTO implements Serializable {
    private String username;
    private String mail;
    private String phone;
    private String role;
    private Integer score;
}
