package com.pro06.controller;

import com.pro06.domain.BoardDTO;
import com.pro06.domain.PageRequestDTO;
import com.pro06.service.BoardService;
import com.pro06.service.BoardServiceImpl;
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

@Log4j2
@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시판 리스트 업
    @GetMapping("/list")
    public String boardList(Model model) {
        return "board/list";
    }

    // 게시판 등록 폼
    @GetMapping("/register")
    public String boardRegisterGET() {
        return "board/register";
    }

    // 게시판 등록
    @PostMapping("/register")
    public String boardRegisterPOST(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.info("======== Board Register Start =========");
        if(bindingResult.hasErrors()) {
            log.info("=====에러 대비=======");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/board/register";
        }
        log.info(boardDTO);
        Long bno  = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result", bno);
        return "redirect:/board/list";
    }

    // 게시판 추가 폼
    @GetMapping("/modify")
    public String modifyForm(Long bno, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);
        model.addAttribute("boardDTO", boardDTO);
        return "board/read";
    }

    // 추가
    @PostMapping("/modify")
    public String boardModify(@Valid BoardDTO boardDTO, PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("======== Board modify Start =========");

        if(bindingResult.hasErrors()) {
            log.info("=====에러 대비=======");
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?"+link;
        }

        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read";
    }


    // 삭제
    @PostMapping("/remove")
    public String boardRemove(Long bno, RedirectAttributes redirectAttributes) {
        log.info("======== Board remove Start =========");
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }
}
