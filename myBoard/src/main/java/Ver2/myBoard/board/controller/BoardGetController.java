package Ver2.myBoard.board.controller;

import Ver2.myBoard.board.dto.BoardDetailDto;
import Ver2.myBoard.board.dto.BoardListDto;
import Ver2.myBoard.board.service.BoardService;
import Ver2.myBoard.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardGetController {

    private final BoardService boardService;

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
    public String boardDetail(@PathVariable("id") Long id, Model model) {
        Board findBoard = boardService.getBoardById(id);
        BoardDetailDto boardDetailDto = new BoardDetailDto(findBoard.getTitle(), findBoard.getContent(), findBoard.getWriter(), findBoard.getComments());

        model.addAttribute("board", boardDetailDto);

        return null;
    }


}
