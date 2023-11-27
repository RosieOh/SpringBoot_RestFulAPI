package com.pro06.controller;

import com.pro06.domain.BoardDTO;
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

    @GetMapping("/list")
    public String List(Model model) {
        return "board/list";
    }

    @GetMapping("/register")
    public String registerGET() {
        return "board/register";
    }

    @PostMapping("/register")
    public String registerPOST(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
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

    @GetMapping("/modify")
    public String modifyForm(Long bno, Model model) {
        BoardDTO boardDTO = boardService.readOne(bno);
        model.addAttribute("boardDTO", boardDTO);
        return "board/read";
    }

    @PostMapping("/modify")
    public String modify(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        BoardDTO
    }
}
