package com.pro06.service;


import com.pro06.dto.MemberJoinDTO;
import com.pro06.entity.Member;
import com.pro06.entity.MemberRole;
import com.pro06.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    // Spring Security에서 PasswordEncoder 빈으로 잡아줌
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException{
        String mid = memberJoinDTO.getMid();
        boolean exist = memberRepository.existsById(mid);
        if(exist){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);
        log.info("=======================");
        log.info(member);
        log.info(member.getRoleSet());
        memberRepository.save(member);
    }

    @Override
    public MemberJoinDTO myinfo(String mid) {
        Member member = memberRepository.findByMid(mid);
        MemberJoinDTO memberDto = modelMapper.map(member, MemberJoinDTO.class);
        return memberDto;
    }
}
