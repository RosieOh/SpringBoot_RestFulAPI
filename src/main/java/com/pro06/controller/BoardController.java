package com.pro06.controller;

import com.pro06.dto.BoardDTO;
import com.pro06.dto.PageRequestDTO;
import com.pro06.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Lombok 어노테이션
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board") // 이 컨트롤러의 모든 엔드포인트에 대한 기본 요청 매핑
@Controller
public class BoardController {

    private final BoardService boardService; // BoardService에 대한 의존성 주입

    // 게시판 목록을 표시하는 엔드포인트
    @GetMapping("/list")
    public String boardList(Model model) {
        return "board/list"; // "board/list" 뷰 이름을 반환
    }

    // 게시판 등록 폼을 표시하는 엔드포인트 (HTTP GET)
    @GetMapping("/register")
    public String boardRegisterGET() {
        return "board/register"; // "board/register" 뷰 이름을 반환
    }

    // 게시판 등록을 처리하는 엔드포인트 (HTTP POST)
    @PostMapping("/register")
    public String boardRegisterPOST(
            @Valid BoardDTO boardDTO, // Bean Validation 어노테이션을 사용하여 입력 BoardDTO를 유효성 검사
            BindingResult bindingResult, // 유효성 검사 결과를 보유하는 객체
            RedirectAttributes redirectAttributes, // 리디렉트 URL에 속성을 추가하기 위해 사용
            Model model) {
        log.info("======== Board Register Start =========");

        // 유효성 검사 오류가 있는 경우, 이를 flash 속성으로 추가하고 등록 폼으로 리디렉트
        if(bindingResult.hasErrors()) {
            log.info("=====에러 대비=======");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        log.info(boardDTO);
        Long bno  = boardService.register(boardDTO); // 새로운 게시글을 등록하기 위해 서비스 호출
        redirectAttributes.addFlashAttribute("result", bno); // 결과를 flash 속성으로 추가
        return "redirect:/board/list"; // 게시판 목록으로 리디렉트
    }

    // 게시판 수정 폼을 표시하는 엔드포인트 (HTTP GET)
    @GetMapping("/modify")
    public String modifyForm(Long bno, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.readOne(bno); // ID에 해당하는 게시글을 검색
        log.info(boardDTO);
        model.addAttribute("boardDTO", boardDTO); // 뷰에 보여질 모델에 boardDTO를 추가
        return "board/read"; // "board/read" 뷰 이름을 반환
    }

    // 게시판 수정을 처리하는 엔드포인트 (HTTP POST)
    @PostMapping("/modify")
    public String boardModify(
            @Valid BoardDTO boardDTO,
            PageRequestDTO pageRequestDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        log.info("======== Board modify Start =========");

        // 유효성 검사 오류가 있는 경우, 이를 flash 속성으로 추가하고 수정 폼으로 리디렉트
        if(bindingResult.hasErrors()) {
            log.info("=====에러 대비=======");
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?" + link;
        }

        boardService.modify(boardDTO); // 게시글을 수정하기 위해 서비스 호출
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read"; // 게시글 상세 페이지로 리디렉트
    }

    // 게시판 삭제를 처리하는 엔드포인트 (HTTP POST)
    @PostMapping("/remove")
    public String boardRemove(Long bno, RedirectAttributes redirectAttributes) {
        log.info("======== Board remove Start =========");
        boardService.remove(bno); // 게시글을 삭제하기 위해 서비스 호출
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list"; // 게시판 목록으로 리디렉트
    }
}
