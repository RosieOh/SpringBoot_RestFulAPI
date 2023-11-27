package com.pro06.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDTO {
    private String id;
    private String pw;
    private String email;
    private boolean del;
    private boolean social;
}
