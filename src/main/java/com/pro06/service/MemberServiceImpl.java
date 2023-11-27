package com.pro06.service;

import com.pro06.dto.MemberJoinDTO;
import com.pro06.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MemberExistException {

    }

    @Override
    public MemberJoinDTO MemberMyAccountinfo(String id) {
        return null;
    }
}
