package my.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.BoardRegisterDTO;
import my.board.service.interfaces.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /*게시판 페이지 --> 페이징 처리 구현 필요
     * URL(GET) : /board
     * */
    @GetMapping
    public String getBoardList(Model model) {
        model.addAttribute("list", boardService.getBoardList());
        log.info("/board");
        return "board/list";
    }

    /*자세한 페이지
     * URL(GET) : /board/{id}
     * */
    @GetMapping("/{id}")
    public String getBoard(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        log.info("/board/{}", id);
        return "board/detail";
    }

    /*단순히 등록 폼으로 이동
     * URL(GET) : /board/new
     * */
    @GetMapping("/new")
    public String registerBoard() {
        return "board/regist";
    }

    /*글 등록
     * ID를 받아와서 detail 창을 보여주고 싶은데 로직 순서를 어떻게 해야할지 모르겠다
     * 지금 떠오르는 것은 @Transactional 어노테이션
     * 일단은 글이 등록되면 board로 redirect하고 Modal창을 띄우는 것으로 하자
     * PRG패턴
     * URL(POST) : /board/new
     * */
    @PostMapping("/new")
    public String postBoard(@ModelAttribute BoardRegisterDTO registerDTO) {
        boardService.insertBoard(registerDTO);
        log.info("/board/new --> POST, BoardRegisterDTO : {}", registerDTO.toString());
        return "redirect:/board";
    }

    /*글 수정 폼
     * URL(GET) : /board/{id}/edit
     * */
    @GetMapping("/{id}/edit")
    public String updateBoard_form(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        return "board/edit";
    }

    /*글 수정 POST
     * URL(POST) : /board/{id}/edit
     * 수정버튼 누르면 detail page로 redirect
     * */
    @PostMapping("/{id}/edit")
    public String updateBoard(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        return "redirect:board/{id}";
    }

    /*글 삭제 POST
    * URL(POST) : /board/{id}/delete
    * */
    @PostMapping("/{id}/delete")
    public String deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return "redirect:board";
    }
}
