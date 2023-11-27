package com.pro06.service;

import com.pro06.dto.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception {}

    void join(MemberJoinDTO memberJoinDTO)throws MidExistException ;

    MemberJoinDTO myinfo(String mid);
}
