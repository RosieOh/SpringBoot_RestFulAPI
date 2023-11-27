package com.pro06.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {
    private String id;          // 사용자 아이디
    private String pw;          // 사용자 비밀번호
    private String email;       // 사용자 이메일
    private boolean del;        // 사용자 계정 삭제 여부
    private boolean social;     // 소셜 로그인 여부
    private Map<String, Object> props;  // 소셜 로그인에서 추가로 받은 사용자 정보

    // 생성자
    public MemberSecurityDTO(String username, String password, String email, boolean del, boolean social,
                             Collection<? extends GrantedAuthority> grantedAuthorities) {
        super(username, password, grantedAuthorities);
        this.id = username;
        this.pw = password;
        this.email = email;
        this.del = del;
        this.social = social;
    }

    // getAttributes 메서드를 통해 소셜 로그인에서 추가로 받은 사용자 정보를 반환
    public Map<String, Object> getAttributes() {
        return this.getProps();
    }

    // getName 메서드를 통해 사용자 이름을 반환
    public String getName() {
        return this.id;
    }
}
