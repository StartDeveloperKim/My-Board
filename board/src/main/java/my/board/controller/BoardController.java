package my.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.Board;
import my.board.domain.BoardRegisterDTO;
import my.board.domain.Criteria;
import my.board.domain.Page;
import my.board.service.interfaces.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String getBoardList(Criteria cri,  Model model) {
        model.addAttribute("list", boardService.getBoardList(cri));
        model.addAttribute("pageMaker", new Page(cri, boardService.getTotal()));
        log.info("/board -> cri = {}", cri.toString());
        return "/board/list";
    }

    /*자세한 페이지
     * URL(GET) : /board/{id}
     * */
    @GetMapping("/{id}")
    public String getBoard(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        //log.info("/board/{}, pageNum={}, amount={}", id, pageNum, amount);

        return "/board/detail";
    }

    /*단순히 등록 폼으로 이동
     * URL(GET) : /board/new
     * */
    @GetMapping("/new")
    public String registerBoard(Model model) {
        /*타임리프에서 사용하기 위한 비어있는 객체 하나 전달*/
        model.addAttribute("board", new Board());
        return "/board/regist";
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
        log.info("id={}", id);

        model.addAttribute("board", boardService.getBoardById(id));
        return "/board/edit";
    }

    /*글 수정 POST
     * URL(POST) : /board/{id}/edit
     * 수정버튼 누르면 detail page로 redirect
     * */
    @PostMapping("/{id}/edit")
    public String updateBoard(@PathVariable int id, Criteria cri, Board board) {
        log.info("updateBoard = {}", board.toString());
        //log.info("Criteria : {}, {}", cri.getPageNum(), cri.getAmount());

        //log.info("pageNum={}, amount={}", pageNum, amount);
        boardService.updateBoard(board);
        return "redirect:/board/{id}?pageNum=" + cri.getPageNum() +"&amount=" + cri.getAmount();
    }

    /*글 삭제 POST
    * URL(POST) : /board/{id}/delete
    * 2022-08-29 삭제버튼이 form안에 위치하면서 GET 요청밖에 되지않는다.
    * 어떻게 해야할지 방법을 찾아보자
    * */
    @GetMapping("/{id}/delete")
    public String deleteBoard(@PathVariable int id, Criteria cri) {
        boardService.deleteBoard(id);
        return "redirect:/board?pageNum=" + cri.getPageNum() +"&amount=" + cri.getAmount();
    }
}
