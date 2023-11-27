package com.pro06.controller;

import com.pro06.dto.MemberJoinDTO;
import com.pro06.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinGET() {
        log.info("============ 회원가입 GET 시작 ============");
        return "member/join";
    }

    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
        log.info("============ 회원가입 POST 시작 ============");
        log.info(memberJoinDTO);
        try {
            memberService.join(memberJoinDTO);
        } catch (MemberService.MemberExistException e) {
            redirectAttributes.addFlashAttribute("error", "id");

            return "redirect:/member/join";
        }
        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginGET() {
        log.info("============ 로그인 GET 시작 ============");
        return "member/login";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/mypage")
    public String myPage(Principal principal, Model model) {
        String id = principal.getName();
        MemberJoinDTO memberJoinDTO = memberService.MemberMyAccountinfo(id);
        log.info("========== MemberMyAccountInfo==========");
        log.info(memberJoinDTO);
        model.addAttribute("memberJoinDTO", memberJoinDTO);
        return "member/mypage";
    }
}
