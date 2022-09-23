package Ver2.myBoard.board.controller;

import Ver2.myBoard.board.dto.BoardDetailDto;
import Ver2.myBoard.board.dto.BoardListDto;
import Ver2.myBoard.board.service.BoardService;
import Ver2.myBoard.domain.Board;
import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.SessionConst;
import Ver2.myBoard.member.dto.MemberLoginDto;
import Ver2.myBoard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardGetController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping
    public String boardForm(Model model) {
        // fetch join을 사용하면 쿼리에 대한 성능이 증가한다.
        List<Board> boardList = boardService.getBoardList();

        // Entity 객체를 직접 model에 담아서 보여주는 것은 옳지 못하다
        // 그래서 따로 DTO 객체를 만들고 이 값들을 초기화해서 넘겨준다.
        List<BoardListDto> boards = boardList.stream()
                .map(b -> new BoardListDto(b.getId(), b.getTitle(),
                        b.getWriter(), b.getHit(), b.getRegDate(), b.getUpdateDate()))
                .collect(Collectors.toList());

        model.addAttribute("boards", boards);

        return "board/listForm";
    }

    @GetMapping("/{id}")
    public String boardDetail(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberLoginDto loginDto,
                              @PathVariable("id") Long id, Model model,
                              RedirectAttributes redirectAttributes) {
        Member member = memberService.getMemberById(loginDto.getId());
        Board findBoard = boardService.getBoardById(id);
        
        // 업데이트 성공 또는 redirect 되었을 때 addFlashAttribute를 하는데 이 경우에는 해당 공간이 비어있지 않기때문
        if (redirectAttributes.getFlashAttributes().isEmpty()) {
            log.info("조회수 증가, {}", redirectAttributes.getFlashAttributes().isEmpty());
            // 오류가 났을 때도 올라가고 있다 --> 원인을 찾자!!!
            boardService.updateHit(findBoard); // 조회수 증가
        }
        
        /*해당글의 작성자와 로그인한 멤버가 동일 인물인지 확인하는 것*/
        if (findBoard.checkMemberEquals(member)) {
            model.addAttribute("status", true);
        } else {
            model.addAttribute("status", false);
        }

        BoardDetailDto boardDetailDto = new BoardDetailDto(findBoard.getId(), findBoard.getTitle(), findBoard.getContent(), findBoard.getWriter(), findBoard.getComments());
        model.addAttribute("board", boardDetailDto);
        model.addAttribute("memberId", member.getId());

        return "board/detailForm";
    }


}
