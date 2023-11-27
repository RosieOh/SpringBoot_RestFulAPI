package com.pro06.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberJoinDTO {
    private String id;
    private String pw;
    private String email;
    private boolean del;
    private boolean social;
}
