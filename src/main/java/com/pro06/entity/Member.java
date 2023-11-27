package com.pro06.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

//사용자 Member 객체
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{

    @Id
    private String mid;
    private String mpw;
    private String email;
    private boolean del;
    private boolean social;
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();
    @Id
    private Long id;

    public void changePassword(String mpw ){
        this.mpw = mpw;
    }
    public void changeEmail(String email){
        this.email = email;
    }
    public void changeDel(boolean del){
        this.del = del;
    }
    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }
    public void clearRoles() {
        this.roleSet.clear();
    }
    public void changeSocial(boolean social){this.social = social;}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}