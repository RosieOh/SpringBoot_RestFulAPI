package com.pro06.service;

import com.pro06.dto.MemberJoinDTO;

public interface MemberService {
    static class MemberExistException extends Exception {}
    void join(MemberJoinDTO memberJoinDTO)throws MemberExistException ;
    MemberJoinDTO MemberMyAccountinfo(String id);
}
