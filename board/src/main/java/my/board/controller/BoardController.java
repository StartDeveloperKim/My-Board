package my.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.Board;
import my.board.domain.BoardRegisterDTO;
import my.board.domain.Criteria;
import my.board.domain.Page;
import my.board.service.interfaces.BoardService;
import my.member.SessionConst;
import my.member.domain.Member;
import org.apache.el.parser.BooleanNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**게시판 페이지 --> 페이징 처리 구현 필요
     * URL(GET) : /board
     * */
    @GetMapping
    public String getBoardList(Criteria cri,  Model model) {
        model.addAttribute("list", boardService.getBoardList(cri));
        model.addAttribute("pageMaker", new Page(cri, boardService.getTotal()));
        //log.info("/board -> cri = {}", cri.toString());
        return "/board/list";
    }

    /**자세한 페이지
     * URL(GET) : /board/{id} <br>
     * 조회수 기능 만들기 --> 해당 페이지 누를때 마다 조회수 1씩 plus <br>
     * 해당 id의 글의 hit를 가져온다 -> +1 연산을 한 뒤에 업데이트 한다.
     * */
    @GetMapping("/{id}")
    public String getBoard(@PathVariable int id, Model model,
                           @RequestParam(required = false, defaultValue = "false") String status) {
        Board board = boardService.getBoardById(id);

        log.info("status = {}", status);
        if (status.equals("true")) {
            boardService.updateHit(board); // 조회수 서비스
        }

        model.addAttribute("board", board);

        return "/board/detail";
    }

    /**단순히 등록 폼으로 이동
     * URL(GET) : /board/new
     * */
    @GetMapping("/new")
    public String registerBoard(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                Model model) {
        // 새 Board 객체에 로그인된 사용자의 닉네임을 미리 설정하여 전달한다.
        // 그럼 View에서 해당 nickname을 뽑아 readonly 설정으로 보여줄 수 있게된다.
        Board board = new Board();
        board.setNickname(loginMember.getNickname());

        model.addAttribute("board", board);
        return "/board/regist";
    }

    /**글 등록
     * ID를 받아와서 detail 창을 보여주고 싶은데 로직 순서를 어떻게 해야할지 모르겠다
     * 지금 떠오르는 것은 @Transactional 어노테이션
     * 일단은 글이 등록되면 board로 redirect하고 Modal창을 띄우는 것으로 하자
     * PRG패턴
     * URL(POST) : /board/new
     * */
    @PostMapping("/new")
    public String postBoard(@Validated @ModelAttribute Board board, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult.getAllErrors());
            return "/board/regist";
        }

        boardService.insertBoard(board);
        log.info("/board/new --> POST, BoardRegisterDTO : {}", board.toString());
        return "redirect:/board";
    }

    /**글 수정 폼
     * URL(GET) : /board/{id}/edit
     * */
    @GetMapping("/{id}/edit")
    public String updateBoard_form(@PathVariable int id, Model model) {
        log.info("id={}", id);

        model.addAttribute("board", boardService.getBoardById(id));
        return "/board/edit";
    }

    /**글 수정 POST
     * URL(POST) : /board/{id}/edit
     * 수정버튼 누르면 detail page로 redirect
     * */
    @PostMapping("/{id}/edit")
    public String updateBoard(@PathVariable int id, Criteria cri, Board board) {
        log.info("updateBoard = {}", board.toString());

        boardService.updateBoard(board);
        return "redirect:/board/{id}?pageNum=" + cri.getPageNum() +"&amount=" + cri.getAmount();
    }

    /**글 삭제 POST
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
