package org.spring.controller;

import java.util.List;

import org.spring.domain.VO;
import org.spring.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        // 전체 회원 목록을 조회
        List<VO> lists = boardService.listAll();

        // JSP 페이지로 회원 목록 전달
        model.addAttribute("lists", lists);
       return "board/list";
    }

    @PostMapping(value = "/insert", produces = "text/html;charset=UTF-8")
    public String insert(@RequestParam("title") String title, @RequestParam("content") String content,
    		@RequestParam("writer") String writer, RedirectAttributes rttr) {
    
        VO vo = new VO();
        vo.setTitle(title);
        vo.setContent(content);
        vo.setWriter(writer);
        int maxBno = boardService.register(vo);
    	rttr.addFlashAttribute("msg", maxBno+"번글이 등록되었습니다.");
        return "redirect:/board/list";
    }
    
    @PostMapping(value = "/update", produces = "text/html;charset=UTF-8")
    public String update(@RequestParam("bno") int bno, @RequestParam("title") String title, 
                         @RequestParam("content") String content, @RequestParam("writer") String writer, Model model) {
        VO vo = new VO();
        vo.setBno(bno);
        vo.setTitle(title);
        vo.setContent(content);
        vo.setWriter(writer);

        int result = boardService.modify(vo);
        log.info("Update Result: " + result);

        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bno") int bno, Model model) {
        int result = boardService.remove(bno);
        log.info("Delete Result: " + result);

        return "redirect:/board/list";
    }
    
    @GetMapping("/updateViewCnt")
    @ResponseBody
    public void updateViewCnt(@RequestParam("bno") int bno) {
        boardService.updateViewCnt(bno);
    }
}
